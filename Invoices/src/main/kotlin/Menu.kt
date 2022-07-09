import java.util.*

class Menu(val repository: Repository) {
    private val menuEntries = arrayOf<MenuEntry>(
        MenuEntry("Show the invoices list", ::displayAllInvoices),
        MenuEntry("Add an invoice", ::addAnInvoice),
        MenuEntry("Delete an invoice", ::deleteAnInvoice)
    )
    public fun printMenu() {
        while(true)
        {
            menuEntries.forEachIndexed { index, element ->
                println("${index + 1}. ${element.title}")
            }
            print("Your choice (type in and press enter): ")
            getUserChoice()
        }
    }
    private fun getUserChoice() {
        val scanner = Scanner(System.`in`)
        val chr = scanner.next().single()

        val parsed = chr.digitToIntOrNull()
        if (parsed != null && parsed > 0 && parsed <= menuEntries.size) {
            menuEntries[parsed - 1].action()
        } else {
            Main.clearConsole()
            println("The option you chose is not correct! Choose a different one")
            printMenu()
        }
    }
    private fun displayAllInvoices() {
        Main.clearConsole()
        println("-- Display all invoices --")
        println("Press Enter to head back to the main menu")

        val invoices = repository.get()
        if(invoices.size === 0) {
            println("You've got no invoices added yet!")
        } else {
            invoices.forEach {
                println("-----")
                println("Number: ${it.num}")
                println("Counterparty name: ${it.counterpartyName}")
                println("Product name: ${it.productName}")
                println("Unit price: $${it.unitPrice}")
                println("Amount: ${it.amount}")
                println("Total price: $${it.unitPrice * it.amount}")
            }
            println("-----")
        }

        readLine()
        Main.clearConsole()
    }
    private fun addAnInvoice() {
        Main.clearConsole()
        println("-- Add an invoice --")

        print("Enter the counterparty name: ")
        var counterpartyName = readLine() ?: ""
        while(!Validator.validateString(counterpartyName)) {
            print("The counterparty name you've provided cannot be empty! Enter a new one:")
            counterpartyName = readLine() ?: ""
        }

        print("Enter the product name: ")
        var productName = readLine() ?: ""
        while(!Validator.validateString(productName)) {
            print("The product name you've provided cannot be empty! Enter a new one:")
            productName = readLine() ?: ""
        }

        print("Enter the price of the product ($): ")
        var productPrice = readLine() ?: ""
        while(
            !Validator.validateFloat(productPrice)
        ) {
            print("The price of the product you've provided is not a valid positive number! Enter a new one: ")
            productPrice = readLine() ?: ""
        }

        print("Enter the amount of product: ")
        var productAmount = readLine() ?: ""
        while(
            !Validator.validateInt(productAmount)
        ) {
            print("The amount of product you've provided is not a valid positive integer number! Enter a new one: ")
            productAmount = readLine() ?: ""
        }

        repository.add(Invoice(
            num = repository.invoices.size + 1,
            counterpartyName = counterpartyName,
            productName = productName,
            unitPrice = productPrice.toFloat(),
            amount = productAmount.toInt()
        ))

        Main.clearConsole()
        println("The invoice has been successfully added. Press Enter to go back to the main menu")
        readLine()
    }
    private fun deleteAnInvoice() {
        Main.clearConsole()
        println("-- Delete an invoice --")
        print("Enter the invoice number you want to delete: ")

        var num = readLine() ?: ""
        while(
            !Validator.validateInt(num) || num.toInt() > repository.invoices.size || num.toInt() <= 0
        ) {
            print("The number you entered is not a correct one/that invoice doesn't exist")
            num = readLine() ?: ""
        }

        repository.delete(num.toInt())
        println("The invoice numbered $num has been deleted! Press enter to go back to the main menu")
        readLine()
    }
}
