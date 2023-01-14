package kz.jusan.singularityhomeworks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileMainTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_main_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(view)
    }

    private fun initButtons(view : View) {
        initShareButton(view)
        initEmailButton(view)
        initCallButton(view)
        initCameraButton(view)
    }

    private fun initShareButton(view : View) {
        val btnShare: TextView = view.findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Here is my profile!")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initEmailButton(view : View) {
        val btnEmail: TextView = view.findViewById(R.id.btn_email)
        btnEmail.setOnClickListener {
            val emailIntent = Intent()
            emailIntent.action = Intent.ACTION_SENDTO
            emailIntent.setData(Uri.parse("mailto:"))
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Here is my profile!")
            startActivity(emailIntent)
        }
    }

    private fun initCallButton(view : View) {
        val btnCall: TextView = view.findViewById(R.id.btn_call)
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+77771234567"))
            startActivity(intent)
        }
    }

    private fun initCameraButton(view : View) {
        val btnCamera: TextView = view.findViewById(R.id.btn_camera)
        btnCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
    }
}