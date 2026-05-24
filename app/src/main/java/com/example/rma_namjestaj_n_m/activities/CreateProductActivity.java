package com.example.rma_namjestaj_n_m.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.widget.ImageView;
import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.database.ProductRepository;
import com.example.rma_namjestaj_n_m.models.Product;

public class CreateProductActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etPrice;
    private EditText etImage;

    private Button btnSave;

    private ProductRepository productRepository;

    //Unos slike
    private ImageView imgPreview;
    private Button btnSelectImage;

    private Uri imageUri;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        imgPreview = findViewById(R.id.imgPreview);
        btnSelectImage = findViewById(R.id.btnSelectImage);

        btnSave = findViewById(R.id.btnSave);

        productRepository = new ProductRepository(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }

        });
        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

            startActivityForResult(intent, PICK_IMAGE);
        });
    }
    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null) {

            imageUri = data.getData();

            if (imageUri != null) {
                // Grant persistable permission to access the image even after app restarts
                final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                
                try {
                    getContentResolver().takePersistableUriPermission(imageUri, takeFlags);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

                imgPreview.setImageURI(imageUri);
            }
        }
    }
    private void saveProduct() {

        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String priceText = etPrice.getText().toString().trim();
        String image = "";

        if (imageUri != null) {
            image = imageUri.toString();
        }

        // Validation
        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(description) ||
                TextUtils.isEmpty(priceText)) {

            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceText);

        // id = 0 because SQLite AUTOINCREMENT usually handles it
        Product product = new Product(
                0,
                name,
                description,
                price,
                image);

        productRepository.insertProduct(product);

        Toast.makeText(this, "Product created successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}