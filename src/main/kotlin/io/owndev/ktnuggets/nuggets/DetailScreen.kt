package io.owndev.ktnuggets.nuggets

import java.util.UUID

// TODO: Work in progress. Finish first the structures then think on the builders

interface Item {
    val id: UUID
}

class Order(override val id: UUID) : Item

class DetailScreen(var orderingList: List<Order> = emptyList(), var listItem: List<Item> = emptyList())

class Text(override val id: UUID, val type: TitleType, val text: String,) : Item

// Elements to be displayed in one line
class Price(override val id: UUID, val titleText: String, val valueText: String) : Item

/*
* Attached under items as a subtext so in the order it has to be right after the item that will have the
* description e.g:
*
* Duration                      €14.08      <- Price Item
* 57min x 0.25 €/min                        <- Detail Item
*
* Elastic Price                 €3.00       <- Price Item
* Due to high demand the price is           <- Detail Item
* higher than normal
* */
class Detail(override val id: UUID, val description: String) : Item

class Separator(override val id: UUID, val type: SeparatorType) : Item

enum class SeparatorType {
    LINE, SECTION
}

/*
* Meant to be used in the same line as prices so in the ordering they have to be right before the element you
* plan to put it in front or right after if you want to place it at the end of the line. e.g:
* 🤑 Elastic Price                 €3.00       <- Price Item
* */
class Icon(override val id: UUID, val iconUrl: String) : Item

fun detailScreen(build: DetailScreen.() -> Unit): DetailScreen {
    val detailScreen = DetailScreen()
    detailScreen.build()
    return detailScreen
}

enum class TitleType {
    TITLE, INFO
}

val details = detailScreen {
}

val json = """
  “title”: “Fixed price”,
  “orderingArray”: [
    {
      “id”: “1”,
      “type”: “OneLine”
    },
    {
      “id”: “0”,
      “type”: “Text”
    },
    {
      “id”: “2”,
      “type”: “TwoLine”
    }
  ],
  
  “Text”: [
    {
      “id”: “0",
      “variant”: “Info”,
      “titleText”: “This is a fixed price based on your pick-up point and destination. Please note that the actual fare might vary if your trip takes much longer than estimated.”
    }
  ],
  
  “OneLine”: [
    {
      “id”: “2”,
      “variant”: “WithValue”,
      “titleText”: “Base price”,
      “valueText”: “5.23 €”
    },
    {
      “id”: “3”,
      “variant”: “Info”,
      “titleText”: “Duration”,
      “valueText”: “14.08 €“,
      “icon”: “”
    }
  ],
  
  “TwoLine”: [
    {
      “id”: “3”,
      “variant”: “Info”,
      “titleText”: “Duration”,
      “descriptionText”: “57min x 0.25 €/min”,
      “valueText”: “14.08 €“,
      “longDescriptionText”: “14.08 €“,
      “icon”: “”
    }
    
  ]
} 
"""