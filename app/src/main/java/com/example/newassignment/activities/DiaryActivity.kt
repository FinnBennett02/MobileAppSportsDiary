package com.example.newassignment.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.newassignment.R
import com.example.newassignment.databinding.ActivityDiaryBinding
import com.example.newassignment.helpers.showImagePicker
import com.example.newassignment.main.MainApp
import com.example.newassignment.models.DiaryModel
import com.example.newassignment.models.Location
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import timber.log.Timber.Forest.i

class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>

    private var diary = DiaryModel()
    private lateinit var app: MainApp
    private var edit = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        registerImagePickerCallback()
        registerMapCallback()


        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        if (intent.hasExtra("diary_edit")) {
            IntentCompat.getParcelableExtra(
                intent,
                "diary_edit",
                DiaryModel::class.java
            )?.let {
                edit = true
                diary = it

                binding.diaryEntryTitle.setText(diary.title)
                binding.description.setText(diary.description)
                binding.goals.setText(diary.goals.toString())
                binding.assit.setText(diary.assist.toString())
                binding.ratingBar.rating = diary.rating.toFloat()
                binding.btnAdd.setText(R.string.save_diary)
                if (diary.image.isNotEmpty()) {
                    Picasso.get()
                        .load(Uri.parse(diary.image))
                        .into(binding.diaryImage)
                    binding.chooseImage.setText(R.string.change_diary_image)
                    }

            }
        }

        binding.btnAdd.setOnClickListener {
            diary.title = binding.diaryEntryTitle.text.toString()
            diary.description = binding.description.text.toString()
            diary.goals = binding.goals.text.toString().toLongOrNull() ?: 0L
            diary.assist = binding.assit.text.toString().toLongOrNull() ?: 0L
            diary.rating = binding.ratingBar.rating.toInt()

            if (diary.title.isEmpty()) {
                Snackbar.make(it, R.string.no_title, Snackbar.LENGTH_LONG).show()
            } else {
                if (edit) {
                    app.diaries.update(diary.copy())
                } else {
                    app.diaries.create(diary.copy())
                }
                setResult(RESULT_OK)
                finish()
            }
        }

        binding.btnDelete.setOnClickListener {
            app.diaries.delete(diary.id.toString())
            Snackbar.make(it, R.string.delete_diary, Snackbar.LENGTH_LONG).show()
            setResult(RESULT_OK)
            finish()
        }

        binding.placemarkLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (diary.zoom != 0f) {
                location.lat =  diary.lat
                location.lng = diary.lng
                location.zoom = diary.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }




    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val imageUri = result.data?.data
                    if (imageUri != null) {
                        diary.image = imageUri.toString()  // store as String
                        Picasso.get()
                            .load(imageUri)  // Picasso can load Uri
                            .into(binding.diaryImage)
                        i("Image selected: $imageUri")
                    }
                }
            }
    }


    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            diary.lat = location.lat
                            diary.lng = location.lng
                            diary.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }







    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_diary, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_cancel -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
