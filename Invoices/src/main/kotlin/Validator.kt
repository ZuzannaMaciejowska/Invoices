class Validator() {
    companion object {
        fun validateString(str: String): Boolean {
            return str.isNotEmpty()
        }
        fun validateInt(str: String): Boolean {
            return str.isNotEmpty()
                    && str.toIntOrNull() != null
                    && str.toFloat() == str.toInt().toFloat()
                    && str.toInt() >= 0//Check if the number is an integer, not float (has no decimal places)
        }
        fun validateFloat(str: String): Boolean {
            return str.isNotEmpty() && str.toFloatOrNull() != null && str.toFloat() >= 0
        }
    }

}