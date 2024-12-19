package com.irza.greenbox.camera


import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustGreen
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CameraScreen(navController: NavController) {
    val context = LocalContext.current
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }

    val viewModel : CameraViewModel = viewModel()

    val permissionLauncher =
        rememberLauncherForActivityResult(

            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                if (!isGranted) {
                    Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
// Check if permission is granted
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        CameraPreview { capture -> imageCapture = capture }
        IconButton(
            onClick = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Scan.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart),

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.White
            )
        }

        // Button di bagian bawah
        IconButton(
            onClick = { takePhoto(context, imageCapture, viewModel) },
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp)
                .background(
                    color = CustGreen,
                    shape = CircleShape
                ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "Scan Now",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }


}
@Composable
fun CameraPreview(onImageCaptureReady: (ImageCapture) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider =
                    cameraProviderFuture.get()

                val preview =
                    Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)

                    }
                val imageCapture =

                    ImageCapture.Builder().build()

                onImageCaptureReady(imageCapture)
                val cameraSelector =
                    CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (exc: Exception) {
                    Log.e("CameraPreview", "Use case binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun takePhoto(
    context: Context,
    imageCapture: ImageCapture?,
    viewModel: CameraViewModel
) {
    if (imageCapture == null) {
        Toast.makeText(context, "ImageCapture is not initialized", Toast.LENGTH_SHORT).show()
        return
    }

    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Images")
        }
    }

    val outputOptions =
        ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri
                if (savedUri != null) {
                    Toast.makeText(context, "Photo saved to gallery", Toast.LENGTH_SHORT).show()

                    // Panggil generateJSON dengan URI gambar
                    val fileName = "$name.jpg"
                    viewModel.generateJSON(savedUri, fileName)
                } else {
                    Toast.makeText(context, "Photo capture failed: URI is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraCapture", "Photo capture failed: ${exception.message}", exception)
            }
        }
    )
}


