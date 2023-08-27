package com.example.kotlin_flowexample.FlowRoom.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.kotlin_flowexample.FlowRoom.db.ContactsEntity
import com.example.kotlin_flowexample.FlowRoom.utils.Constants.BUNDLE_ID
import com.example.kotlin_flowexample.FlowRoom.utils.Constants.EDIT
import com.example.kotlin_flowexample.FlowRoom.utils.Constants.NEW
import com.example.kotlin_flowexample.FlowRoom.viewmodel.DatabaseViewModel
import com.example.kotlin_flowexample.R
import com.example.kotlin_flowexample.databinding.FragmentAddContactBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddContactFragment : DialogFragment() {

    @Inject
    lateinit var entity: ContactsEntity

    private val viewModel: DatabaseViewModel by viewModels()

    private var contactId = 0
    private var name = ""
    private var phone = ""

    private var type = ""
    private var isEdit = false

    private lateinit var binding: FragmentAddContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactId = arguments?.getInt(BUNDLE_ID) ?: 0

        if (contactId > 0) {
            type = EDIT
            isEdit = true
        } else {
            type = NEW
            isEdit = false
        }

        binding.apply {
            imgClose.setOnClickListener { dismiss() }

            if (type == EDIT) {
                viewModel.getDetailsContact(contactId)
                viewModel.contactDetails.observe(viewLifecycleOwner) { itData ->
                    itData.data?.let {
                        edtName.setText(it.name)
                        edtPhone.setText(it.phone)
                    }
                }
            }


            btnSave.setOnClickListener {
                name = edtName.text.toString()
                phone = edtPhone.text.toString()
                if (name.isEmpty() || phone.isEmpty()) {
                    Snackbar.make(it, "Name and Phone cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    entity.id = contactId
                    entity.name = name
                    entity.phone = phone

                    viewModel.saveContact(isEdit, entity)

                    edtName.setText("")
                    edtPhone.setText("")

                    dismiss()

                }
            }
        }

    }

}