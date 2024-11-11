package com.example.colorpicker

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.github.skydoves.colorpicker.compose.*
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap

//@Composable
//fun ImageColorPickers() {
//    val controller = rememberColorPickerController()
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
//    val context = LocalContext.current
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) {uri : Uri? ->
//        imageUri =uri
//        imageUri?.let{
//            val inputStream = context.contentResolver.openInputStream(it)
//            val drawable = Drawable.createFromStream(inputStream, it.toString())
//            imageBitmap = drawable?.toBitmap()?.asImageBitmap()
//        }
//
//    }
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        ImageColorPicker(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(400.dp),
//            controller =controller,
//            paletteImageBitmap = imageBitmap ?: ImageBitmap.imageResource(R.drawable.wallpaper),
//            onColorChanged = {
//                Log.d("Color", "selected color is $it")
//            }
//        )
//        Button(onClick = { launcher.launch("image/*") }) {
//            Text(text = "Pick Image")
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        Spacer(modifier = Modifier.height(12.dp))
//        Row (
//            modifier = Modifier.fillMaxWidth()
//                .padding(horizontal = 30.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            AlphaTile(
//                modifier = Modifier.fillMaxWidth()
//                    .height(60.dp)
//                    .padding(10.dp),
//                controller =controller
//            )
//        }
//        AlphaSlider(
//            modifier = Modifier.fillMaxWidth()
//                .padding(10.dp)
//                .height(35.dp),
//            controller = controller
//        )
//        BrightnessSlider(
//            modifier = Modifier.fillMaxWidth()
//                .padding(10.dp)
//                .height(35.dp),
//            controller = controller
//        )
//    }
//}

@Composable
fun ImageColorPickers() {
    val controller = rememberColorPickerController()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        imageUri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val drawable = Drawable.createFromStream(inputStream, it.toString())
            imageBitmap = drawable?.toBitmap()?.asImageBitmap()
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            ImageColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(top=10.dp),
                controller = controller,
                paletteImageBitmap = imageBitmap ?: ImageBitmap.imageResource(R.drawable.wallpaper),
                onColorChanged = {
                    Log.d("Color", "selected color is $it")
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Button(
                onClick = { launcher.launch("image/*") }) {
                Text(text = "Pick Image")
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AlphaTile(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(10.dp),
                    controller = controller
                )
            }
        }
        item {
            AlphaSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = controller
            )
        }
        item {
            BrightnessSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = controller
            )
        }
    }
}