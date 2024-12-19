package com.irza.greenbox.camera

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.storage.UploadTask.TaskSnapshot
import com.google.firebase.storage.storage
import com.google.firebase.vertexai.type.content
import com.google.firebase.vertexai.vertexAI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CameraViewModel: ViewModel() {

    private val _generatedJSON = MutableStateFlow("")
    val generatedJSON: StateFlow<String> get() = _generatedJSON
    private val storage = Firebase.storage
    private var storageRef = storage.getReference("images")

    private suspend fun uploadImage(imageUri: Uri, fileName: String): TaskSnapshot? {
        return try {
            storageRef = storageRef.child(fileName)
            storageRef.putFile(imageUri).await()
        } catch (e: Exception) {
            Log.e("Upload", "Error uploading image: ${e.message}")
            null
        }
    }


    private val vertexAI = Firebase.vertexAI
    private val model = vertexAI.generativeModel("gemini-1.5-flash")

    fun generateJSON(imageUri: Uri, fileName: String){
        viewModelScope.launch {

            val taskSnapshot = uploadImage(imageUri, fileName)

            val mimeType = taskSnapshot?.metadata?.contentType
            val bucket = taskSnapshot?.metadata?.bucket
            val filePath = taskSnapshot?.metadata?.path

            val storageUri = "gs://$bucket/$filePath"

            if (mimeType != null && bucket != null) {
                val prompt = content {
                    fileData(storageUri, mimeType) // Menyisipkan URI dan tipe konten dari file gambar.
                    text(
                        "detect the image and clasify to organic or non-organic trash"
                    )
                }

                model.generateContentStream(prompt).map { it.text.orEmpty() }.collect{
                    _generatedJSON.value += it
                    Log.d("TAG", "generateJSON: $it")
                }
            }
        }
    }
}