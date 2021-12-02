package com.example.wheel.Model


val sectorsint = arrayOf("0","0","0","0")

val sectors = arrayOf(
    "bankrupt","1000 points","miss turn","extra turn"
)

private var Sectors  : String = ""
private var lives = 0
var scores = 0

fun getSectors(): String {
    return Sectors
}

fun setSectors(Sector: String){
    Sectors = Sector
}


fun setLife(life: Int){
    lives = life
}
fun getLife() : Int{
    return lives
}
fun plusLife(){
  // lives = life
   lives ++

}

fun minusLife(){
   // lives = life
    lives --
}

fun setScore(score: Int){
    scores = score
}

fun plusScores(){
   // scores = score
    scores += 1000
}


fun losesAllScores(){
    scores = 0
}


fun updateScore(score: Int) {
    scores = score
}

fun getScore(): Int {
    return scores
}

var randomWord = ""

var myLetter: String = ""

var hiddenWordArrays = Array<String>(0) { "it = $it" }
private var hiddenWord: String = ""

var messages: String = ""


private val MyRandomWord = listOf(
    "OMAR","EBRAHIM","HI","MAR"
)

private val MyRandomScore = listOf(
    1000,500,3000,2000,1500,2500
)

fun getARandomWord(): String {

    var rnds = (0..3).random()
    randomWord = MyRandomWord.get(rnds)
    return randomWord
}

fun getRandomscores(): Int{
    return MyRandomScore.get((0..5).random())
}

fun hiddenWord(): String {
    var wordToBeHidden = randomWord
    hiddenWordArrays = Array<String>(wordToBeHidden.length) { "it = $it" }

    for (i in wordToBeHidden.indices) {
        hiddenWordArrays[i] = "#"
    }
    val list: String = hiddenWordArrays.toList().toString()

        .replace(",", "")
        .replace("[", "")
        .replace("]", "")

    hiddenWord = list
    return list
}



fun updateHiddenWord(): String{
    for (i in randomWord.indices){
        if(randomWord.get(i) == myLetter.get(0) && hiddenWordArrays[i] == "#"){
            hiddenWordArrays[i] = myLetter.get(0).toString()
        }
    }
    return hiddenWordArrays.toList().toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")

}

