package com.github.seanse.ean13

/**
 * @author JOHN
 */
class EAN13CodeBuilder(private val codeString: String) {

    fun getCode(): String {
        val fullString = getFullCode()
        return createEAN13Code(fullString)
    }

    private fun getFullCode(): String {
        var chetVal = 0
        var nechetVal = 0
        var codeToParse = codeString
        for (index in 0..5) {
            chetVal += codeToParse.substring(index * 2 + 1, index * 2 + 2).toInt()
            nechetVal += codeToParse.substring(index * 2, index * 2 + 1).toInt()
        }
        chetVal *= 3
        var controlNumber = 10 - (chetVal + nechetVal) % 10
        if (controlNumber == 10) controlNumber = 0
        codeToParse += controlNumber.toString()
        return codeToParse
    }

    private fun createEAN13Code(rawCode: String?): String {
        val firstFlag = rawCode!!.substring(0, 1).toInt()
        val leftString = rawCode.substring(1, 7)
        val rightString = rawCode.substring(7)
        val rightCode = StringBuilder()
        var leftCode = ""
        for (i in 0..5) {
            rightCode.append(digitToLowerCase(rightString.substring(i, i + 1)))
        }
        if (firstFlag == 0) {
            leftCode = ("#!" + leftString[0]
                    + leftString[1]
                    + leftString[2]
                    + leftString[3]
                    + leftString[4]
                    + leftString.substring(5))
        }
        if (firstFlag == 1) {
            leftCode = ("$!" + leftString[0]
                    + leftString[1]
                    + digitToUpperCase(leftString.substring(2, 3))
                    + leftString[3]
                    + digitToUpperCase(leftString.substring(4, 5))
                    + digitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 2) {
            leftCode = ("%!" + leftString[0]
                    + leftString.substring(1, 2)
                    + digitToUpperCase(leftString.substring(2, 3))
                    + digitToUpperCase(leftString.substring(3, 4))
                    + leftString[4]
                    + digitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 3) {
            leftCode = ("&!" + leftString[0]
                    + leftString[1]
                    + digitToUpperCase(leftString.substring(2, 3))
                    + digitToUpperCase(leftString.substring(3, 4))
                    + digitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        if (firstFlag == 4) {
            leftCode = ("'!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + leftString[2]
                    + leftString[3]
                    + digitToUpperCase(leftString.substring(4, 5))
                    + digitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 5) {
            leftCode = ("(!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + digitToUpperCase(leftString.substring(2, 3))
                    + leftString[3]
                    + leftString[4]
                    + digitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 6) {
            leftCode = (")!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + digitToUpperCase(leftString.substring(2, 3))
                    + digitToUpperCase(leftString.substring(3, 4))
                    + leftString[4]
                    + leftString.substring(5))
        }
        if (firstFlag == 7) {
            leftCode = ("*!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + leftString[2]
                    + digitToUpperCase(leftString.substring(3, 4))
                    + leftString[4]
                    + digitToUpperCase(leftString.substring(5)))
        }
        if (firstFlag == 8) {
            leftCode = ("+!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + leftString[2]
                    + digitToUpperCase(leftString.substring(3, 4))
                    + digitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        if (firstFlag == 9) {
            leftCode = (",!" + leftString[0]
                    + digitToUpperCase(leftString.substring(1, 2))
                    + digitToUpperCase(leftString.substring(2, 3))
                    + leftString[3]
                    + digitToUpperCase(leftString.substring(4, 5))
                    + leftString.substring(5))
        }
        return "$leftCode-$rightCode!"
    }

    private fun digitToUpperCase(digit: String): String {
        val letters = "ABCDEFGHIJ"
        val position = digit.toInt()
        return letters.substring(position, position + 1)
    }

    private fun digitToLowerCase(digit: String): String {
        val letters = "abcdefghij"
        val position = digit.toInt()
        return letters.substring(position, position + 1)
    }
}
