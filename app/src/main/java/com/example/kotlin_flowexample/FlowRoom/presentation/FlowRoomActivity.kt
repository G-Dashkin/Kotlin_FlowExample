package com.example.kotlin_flowexample.FlowRoom.presentation

import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_flowexample.FlowRoom.adapter.ContactsAdapter
import com.example.kotlin_flowexample.FlowRoom.utils.Constants.BUNDLE_ID
import com.example.kotlin_flowexample.FlowRoom.utils.DataStatus
import com.example.kotlin_flowexample.FlowRoom.viewmodel.DatabaseViewModel
import com.example.kotlin_flowexample.FlowRoom.viewmodel.isVisible
import com.example.kotlin_flowexample.R
import com.example.kotlin_flowexample.databinding.ActivityFlowRoomBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import javax.inject.Inject

@AndroidEntryPoint
class FlowRoomActivity : AppCompatActivity() {

    @Inject
    lateinit var contactsAdapter: ContactsAdapter
    private val viewModel: DatabaseViewModel by viewModels()
    private lateinit var binding: ActivityFlowRoomBinding
    var selectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setSupportActionBar(mainToolbar)

            btnShowDialog.setOnClickListener {
                AddContactFragment().show(supportFragmentManager, AddContactFragment().tag)
            }

            viewModel.getAllContacts()
            viewModel.contactsList.observe(this@FlowRoomActivity) {
                when(it.status) {
                    DataStatus.Status.LOADING -> {
                        loading.isVisible(true, rvContacts)
                        emptyBody.isVisible(false, rvContacts)
                    }
                    DataStatus.Status.SUCCESS -> {
                        it.isEmpty?.let {
                            showEmpty(it)
                        }
                        loading.isVisible(false, rvContacts)
                        contactsAdapter.differ.submitList(it.data)
                        rvContacts.apply {
                            layoutManager = LinearLayoutManager(this@FlowRoomActivity)
                            adapter = contactsAdapter
                        }
                    }
                    DataStatus.Status.ERROR->{
                        loading.isVisible(false, rvContacts)
                        Toast.makeText(this@FlowRoomActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            mainToolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.actionDeleteAll -> {
                        DeleteAllContactsFragment().show(supportFragmentManager, DeleteAllContactsFragment().tag)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.actionSort -> {
                        filter()
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }

            val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val contact = contactsAdapter.differ.currentList[position]
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            viewModel.deleteContact(contact)
                            Snackbar.make(binding.root, "Item Deleted!", Snackbar.LENGTH_LONG).apply {
                                setAction("UNDO") {
                                    viewModel.saveContact(false, contact)
                                }
                            }.show()
                        }
                        ItemTouchHelper.RIGHT -> {
                            val addContactFragment = AddContactFragment()
                            val bundle = Bundle()
                            bundle.putInt(BUNDLE_ID, contact.id)
                            addContactFragment.arguments = bundle
                            addContactFragment.show(supportFragmentManager, AddContactFragment().tag)
                        }
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftLabel("Delete")
                        .addSwipeLeftBackgroundColor(Color.RED)
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .setSwipeLeftActionIconTint(Color.WHITE)
                        .addSwipeRightLabel("Edit")
                        .addSwipeRightBackgroundColor(Color.GREEN)
                        .setSwipeRightLabelColor(Color.WHITE)
                        .setSwipeRightActionIconTint(Color.WHITE)
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                        .create()
                        .decorate()
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }

            }

            val itemTouchHelper = ItemTouchHelper(swipeCallback)
            itemTouchHelper.attachToRecyclerView(rvContacts)

        }
    }
    fun showEmpty(isShow: Boolean) {
        binding.apply {
            if (isShow) {
                emptyBody.isVisible(true, listBody)
            } else {
                emptyBody.isVisible(false, listBody)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val search = menu.findItem(R.id.actionSearch)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchContact(newText!!)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun filter() {
        val builder = AlertDialog.Builder(this)
        val sortItems = arrayOf("Newer(Default)", "Name : A-Z", "Name Z-A")
        builder.setSingleChoiceItems(sortItems, selectedItem) { dialog, item ->
            when (item) {
                0 -> {
                    viewModel.getAllContacts()
                }
                1 -> {
                    viewModel.sortedASC()
                }
                2 -> {
                    viewModel.sortedDESC()
                }
            }
            selectedItem = item
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }








}