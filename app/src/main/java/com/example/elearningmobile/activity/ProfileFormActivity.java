package com.example.elearningmobile.activity;

import static com.example.elearningmobile.ultity.FileUtils.getFileFromUri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.api.MediaApi;
import com.example.elearningmobile.api.StudentApi;
import com.example.elearningmobile.fragment.ProfileFragment;
import com.example.elearningmobile.model.MediaResponse;
import com.example.elearningmobile.model.StudentPutVM;
import com.example.elearningmobile.model.UserVm;
import com.example.elearningmobile.ultity.FileUtils;
import com.example.elearningmobile.variable.GlobalVariable;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.Manifest;
public class ProfileFormActivity extends AppCompatActivity {
    EditText ed_firstName_profile, ed_lastName_profile, ed_password_profile;

    Button show_date_picker_btn, buttonChooseImage, btn_cancel, btn_edit_profile, btn_backToProfile_profileForm;
    private static final int PERMISSION_REQUEST_CODE = 100;
    GlobalVariable globalVariable = new GlobalVariable();

    List<String> genderOptions = Arrays.asList("NAM", "NỮ");

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView iv_userPhoto;

    Spinner sp_gender;
    EditText ed_dateOfBirth_profile;

    int day, month, year;

    String gender;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Xử lý kết quả trả về từ Android Image Picker
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Lấy URI của ảnh được chọn
            imageUri = data.getData();
            getContentResolver().takePersistableUriPermission(imageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            //imgLogoTH.setImageURI(selectedImageUri);
            Picasso.get().load(imageUri).into(iv_userPhoto);


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, PERMISSION_REQUEST_CODE);
            }
        }
        setControl();
        setEvent();
    }



    private void setEvent() {

       globalVariable = (GlobalVariable) getApplication();
        if (globalVariable.isLoggedIn()) {
            UserVm userVm = globalVariable.getAuth();
            ed_firstName_profile.setText(userVm.getFirstName());
            ed_lastName_profile.setText(userVm.getLastName());
            ed_dateOfBirth_profile.setText(userVm.getDateOfBirth());

            if (userVm.getGender() != "" && userVm.getGender() != null) {
                int defaultPosition = genderOptions.indexOf(userVm.getGender());
                if (defaultPosition != -1) {
                    sp_gender.setSelection(defaultPosition);
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_gender.setAdapter(adapter);
        show_date_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateOfBirth();
            }
        });


        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btn_backToProfile_profileForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToProfileFragment();
            }
        });

        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                if (selectedGender.equals("NAM")) {
                    gender = "MALE";
                } else if (selectedGender.equals("NỮ")) {
                    gender = "FEMALE";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional handling for no selection
            }
        });
        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (globalVariable.isLoggedIn()) {
                    UserVm userVm = globalVariable.getAuth();
                    StudentPutVM studentPutVM = new StudentPutVM();
                    studentPutVM.setId(userVm.getId());
                    studentPutVM.setFirstName(ed_firstName_profile.getText().toString().trim());
                    studentPutVM.setLastName(ed_lastName_profile.getText().toString().trim());
                    studentPutVM.setEmail(userVm.getEmail());
                    studentPutVM.setDay(day);
                    studentPutVM.setMonth(month);
                    studentPutVM.setYear(year);
                    if (gender != null) {
                        studentPutVM.setGender(gender);
                    }

                    if (imageUri != null) {
                        File file = getFileFromUri(getApplicationContext(), imageUri);
                        RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), "image");
                        RequestBody image = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part requestImage = MultipartBody.Part.createFormData("file", file.getName(), image);
                        MediaApi.mediaApi.save(requestImage, type).enqueue(new Callback<MediaResponse>() {
                            @Override
                            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                                MediaResponse mediaDto = response.body();
                                studentPutVM.setPhoto(mediaDto.getUrl());
                            }
                            @Override
                            public void onFailure(Call<MediaResponse> call, Throwable t) {
                            }
                        });

                    }

                    if (ed_password_profile.getText().toString() != "") {
                        studentPutVM.setPassword(ed_password_profile.getText().toString());
                    } else {
                        studentPutVM.setPassword("");
                    }

                    StudentApi.studentApi.updateProfile(studentPutVM).enqueue(new Callback<UserVm>() {
                        @Override
                        public void onResponse(Call<UserVm> call, Response<UserVm> response) {
                            UserVm updatedUser = response.body();
                            globalVariable.setAuth(updatedUser);
                            Toast.makeText(getApplicationContext(), "Cập nhật người dùng thành công", Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onFailure(Call<UserVm> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                        }
                    });
                }


            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToProfileFragment();
            }
        });
    }

    private void redirectToProfileFragment() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("fragment", R.id.nav_profile);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }



    private void setDateOfBirth() {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(ProfileFormActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int newYear,
                                          int monthOfYear, int dayOfMonth) {
                        String formatedMonth = String.format("%02d", monthOfYear + 1);
                        String formatedDay = String.format("%02d", dayOfMonth);
                        // Display Selected date in textbox
                        ed_dateOfBirth_profile.setText(newYear + "-"
                                + formatedMonth + "-" + formatedDay);

                        day = dayOfMonth;
                        month = monthOfYear + 1;
                        year = newYear;

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }



    private void setControl() {
        ed_firstName_profile = findViewById(R.id.ed_firstName_profile);
        ed_lastName_profile = findViewById(R.id.ed_lastName_profile);
        ed_password_profile = findViewById(R.id.ed_password_profile);
        show_date_picker_btn = findViewById(R.id.show_date_picker_btn);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        sp_gender = findViewById(R.id.sp_gender);
        ed_dateOfBirth_profile = findViewById(R.id.ed_dateOfBirth_profile);
        iv_userPhoto = findViewById(R.id.iv_userPhoto);
        btn_backToProfile_profileForm = findViewById(R.id.btn_backToProfile_profileForm);
    }
}