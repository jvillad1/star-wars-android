package com.jvillad.starwars.android.presentation.search.adapter.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.setOnDebouncedClickListener
import com.jvillad.starwars.android.presentation.search.adapter.controller.CharactersController.CharacterItemListener
import com.jvillad.starwars.android.presentation.search.adapter.model.CharacterItemModel.CharacterHolder
import com.jvillad.starwars.android.presentation.search.model.CharacterUI

/**
 * EpoxyModelClass for the Character list.
 *
 * @author juan.villada
 */
@EpoxyModelClass(layout = R.layout.item_character)
abstract class CharacterItemModel : EpoxyModelWithHolder<CharacterHolder>() {

    @EpoxyAttribute
    lateinit var characterUI: CharacterUI

    @EpoxyAttribute
    lateinit var itemListener: CharacterItemListener

    override fun bind(holder: CharacterHolder) = with(holder) {
        characterGenderTextView.text = characterUI.name
        characterGenderTextView.text = characterUI.name

        container.setOnDebouncedClickListener {
            itemListener.onCharacterClicked(characterUI)
        }
    }

    inner class CharacterHolder : EpoxyHolder() {
        lateinit var characterNameTextView: ImageView
        lateinit var characterGenderTextView: TextView
        lateinit var container: View

        override fun bindView(itemView: View) {
            characterNameTextView = itemView.findViewById(R.id.characterNameTextView)
            characterGenderTextView = itemView.findViewById(R.id.characterGenderTextView)
            container = itemView
        }
    }
}
