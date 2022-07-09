import com.google.gson.Gson
import java.io.File

class Repository() {
    public var invoices: MutableList<Invoice> = mutableListOf()
    private val file = File("invoices.json")
    private val gson = Gson()

    init {
        if(!file.exists()) {
            file.createNewFile()
        }
        get()
    }
    public fun get(): List<Invoice> {
        val content = file.inputStream().readBytes().toString(Charsets.UTF_8)
        invoices = if(content.isNotEmpty()) {
            val parsed = gson.fromJson(content, Array<Invoice>::class.java).toMutableList() as MutableList<Invoice>
            parsed
        } else {
            mutableListOf()
        }
        return invoices
    }
    private fun save() {
        val parsedToJson = gson.toJson(invoices)
        file.writeText(parsedToJson)
    }
    public fun add(invoice: Invoice) {
        invoices.add(invoice)
        save()
    }
    public fun delete(invoiceNum: Int) {
        invoices.removeAt(invoiceNum - 1)
        rearangeNumbers()
        save()
    }
    private fun rearangeNumbers() {
        invoices.forEachIndexed() { index, element ->
            element.num = index + 1
        }
    }
}