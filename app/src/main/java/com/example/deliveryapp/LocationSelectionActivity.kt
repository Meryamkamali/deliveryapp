package com.example.deliveryapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LocationSelectionActivity : AppCompatActivity() {

    private lateinit var rvTimeline: RecyclerView
    private val timelineItems = mutableListOf<TimelineItem>()
    private lateinit var adapter: TimelineAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_selection)

        // Initialisation des vues
        rvTimeline = findViewById(R.id.rvTimeline)
        val btnFacebook: ImageButton = findViewById(R.id.btnFacebook)
        val btnTwitter: ImageButton = findViewById(R.id.btnTwitter)
        val btnShare: ImageButton = findViewById(R.id.btnShare)

        setupTimelineItems()
        setupRecyclerView()
        setupSocialSharing(btnFacebook, btnTwitter, btnShare)
    }

    private fun setupTimelineItems() {
        timelineItems.addAll(listOf(
            TimelineItem("Modify Ditta AI Aloha", "Tomorrow", false),
            TimelineItem("Addictionumm", "Timeline", false),
            TimelineItem("Yourself for Templates", "Tomorrow", false),
            // ... autres éléments
        ))
    }

    private fun setupRecyclerView() {
        adapter = TimelineAdapter(timelineItems) { position ->
            val item = timelineItems[position]
            item.isSelected = !item.isSelected
            adapter.notifyItemChanged(position)
        }

        rvTimeline.apply {
            layoutManager = LinearLayoutManager(this@LocationSelectionActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = this@LocationSelectionActivity.adapter
        }
    }

    private fun setupSocialSharing(
        btnFacebook: ImageButton,
        btnTwitter: ImageButton,
        btnShare: ImageButton
    ) {
        btnFacebook.setOnClickListener { shareContent(SocialPlatform.FACEBOOK) }
        btnTwitter.setOnClickListener { shareContent(SocialPlatform.TWITTER) }
        btnShare.setOnClickListener { shareContent(SocialPlatform.GENERIC) }
    }

    // Les autres méthodes restent identiques
    private fun shareContent(platform: SocialPlatform) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, generateShareText())
        }

        when(platform) {
            SocialPlatform.FACEBOOK -> {
                shareIntent.setPackage("com.facebook.katana")
                startActivity(shareIntent)
            }
            SocialPlatform.TWITTER -> {
                shareIntent.setPackage("com.twitter.android")
                startActivity(shareIntent)
            }
            SocialPlatform.GENERIC -> {
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("timelineItems", ArrayList(timelineItems))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedItems = savedInstanceState.getParcelableArrayList<TimelineItem>("timelineItems")
        savedItems?.let {
            timelineItems.clear()
            timelineItems.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}


