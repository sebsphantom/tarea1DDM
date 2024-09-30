package com.sebs.bodybuildinglegends.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.sebs.bodybuildinglegends.R
import com.sebs.bodybuildinglegends.databinding.LegendDialogBinding
import java.lang.StringBuilder
import com.google.android.material.textfield.TextInputEditText
import com.sebs.bodybuildinglegends.application.BodybuildingLegendsDBApp
import com.sebs.bodybuildinglegends.data.LegendRepository
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class LegendDialog(
    private var legend: LegendEntity = LegendEntity(
        title = "",
        category = "",
        region = ""
    ),
    private  val updateUI: () -> Unit
): DialogFragment() {

    private var _binding: LegendDialogBinding? = null
    private val  binding get() = _binding!!

    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: Dialog

    private var saveButton: Button? = null

    private lateinit var repository: LegendRepository

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = LegendDialogBinding.inflate(requireActivity().layoutInflater)

        repository = (requireContext().applicationContext as BodybuildingLegendsDBApp).repository

        builder = AlertDialog.Builder(requireContext())

        dialog = builder.setView(binding.root)
            .setTitle(getString(R.string.legend))
            .setPositiveButton("Guardar") { dialog, which ->
                binding.apply {
                    legend.title = tietTitle.text.toString()
                    legend.category = tietGenre.text.toString()
                    legend.region = tietDeveloper.text.toString()
                }

                try {
                    lifecycleScope.launch(Dispatchers.IO) {
                        repository.insertLegend(legend)
                    }

                    Toast.makeText(
                        requireContext(),
                        "Leyenda guardada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    updateUI()

                    dismiss() // Cierra el diálogo tras guardar

                } catch (e: IOException) {
                    Toast.makeText(
                        requireContext(),
                        "Error al guardar la leyenda",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("Cancelar") { dialog, which -> }
            .create()

        return dialog
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        val alertDialog = dialog as AlertDialog
        saveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        saveButton?.isEnabled = false

        binding.tietTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }
        })

        binding.tietGenre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }
        })

        binding.tietDeveloper.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }
        })
    }

    private fun validateFields(): Boolean =
        binding.tietTitle.text.toString().isNotEmpty() &&
                binding.tietGenre.text.toString().isNotEmpty() &&
                binding.tietDeveloper.text.toString().isNotEmpty()
}

