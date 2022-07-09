import java.io.Serializable

data class Invoice(var num: Int, var counterpartyName: String, var productName: String, var unitPrice: Float, var amount: Int) :
    Serializable {}
