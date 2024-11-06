package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.api.MediaApi;
import com.example.elearningmobile.model.MediaResponse;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFormActivity extends AppCompatActivity {
    EditText ed_firstName_profile, ed_lastName_profile, ed_password_profile;

    Button show_date_picker_btn, buttonChooseImage, btn_cancel, btn_edit_profile;
    Spinner sp_gender;
    TextView ed_dateOfBirth_profile;

    private static final int REQUEST_CODE_GALLERY = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);
        setControl();
        setEvent();
    }

    private void setEvent() {
        List<String> genderOptions = Arrays.asList("MALE", "FEMALE");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_gender.setAdapter(adapter);
        show_date_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateOfBirth();
            }
        });

        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                // Do something with the selected gender (e.g., save to a variable)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional handling for no selection
            }
        });
    }private void setDateOfBirth() {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(ProfileFormActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String month = String.format("%02d", monthOfYear + 1);
                        String day = String.format("%02d", dayOfMonth);
                        // Display Selected date in textbox
                        ed_dateOfBirth_profile.setText(year + "-"
                                + month + "-" + day);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    // Convert URI to File
                    File file = new File(getPathFromUri(imageUri));
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                    // Additional parameter
                    RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "yourType");

                    // Send API request
                    uploadImage(body, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void uploadImage(MultipartBody.Part filePart, RequestBody typePart) {

        MediaApi.mediaApi.save(filePart, typePart).enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Upload", "Upload successful");
                } else {
                    Log.d("Upload", "Upload failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Log.e("Upload", "Error: " + t.getMessage());

            }
        });

    }
    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
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
    }
}