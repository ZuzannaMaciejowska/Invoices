
import com.google.gson.Gson
import java.io.File
import java.io.Serializable
import java.util.*


fun main(args: Array<String>) {
    val mainInstance = Main()
    mainInstance.handleMenu()
}

class Main() {
    private val repository = Repository()

    public fun handleMenu() {
        val menu = Menu(repository)

        clearConsole()
        menu.printMenu()
    }
    companion object {
        fun clearConsole() {
            val os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
    }

}










