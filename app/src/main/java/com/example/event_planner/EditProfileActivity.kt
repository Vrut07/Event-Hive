package com.example.event_planner

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.event_planner.databinding.ActivityEditProfileBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var mobile: String
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mobile = intent.getStringExtra("mobile") ?: ""

        // Load current user data
        loadUserData()

        // Select new image
        binding.changeImageIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(intent, 1001)
        }

        // Save changes
        binding.btnSaveProfile.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val email = binding.editEmail.text.toString().trim()

            if (name.isEmpty()) {
                binding.editName.error = "Name is required"
                return@setOnClickListener
            }

            if (imageUri != null) {
                uploadImageToFirebase(imageUri!!) { imageUrl ->
                    saveToFirebase(name, email, imageUrl)
                }
            } else {
                saveToFirebase(name, email, null)
            }
        }
    }

    private fun loadUserData() {
        val ref = FirebaseDatabase.getInstance().reference.child("Users").child(mobile)
        ref.get().addOnSuccessListener { snapshot ->
            val name = snapshot.child("name").value?.toString()
            val email = snapshot.child("email").value?.toString()
            val imageUrl = snapshot.child("imageUrl").value?.toString()

            binding.editName.setText(name)
            binding.editEmail.setText(email)

            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(imageUrl).into(binding.editProfileImage)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load profile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveToFirebase(name: String, email: String, imageUrl: String?) {
        val ref = FirebaseDatabase.getInstance().reference.child("Users").child(mobile)
        val updates = mutableMapOf<String, Any>()
        updates["name"] = name
        updates["email"] = email
        if (imageUrl != null) updates["imageUrl"] = imageUrl

        ref.updateChildren(updates).addOnSuccessListener {
            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to update profile: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImageToFirebase(uri: Uri, callback: (String) -> Unit) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val storageRef = FirebaseStorage.getInstance().reference.child("profile_images/$mobile.jpg")
        val uploadTask = storageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            // Confirm the file exists before getting URL
            storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                progressDialog.dismiss()
                callback(downloadUri.toString())
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Download URL failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(this, "Image upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            if (imageUri != null) {
                binding.editProfileImage.setImageURI(imageUri)
            } else {
                Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
