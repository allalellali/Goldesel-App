package com.example.goldeselelsdorf

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var orderType: String = "pickup"
    private var cartTotal: Double = 0.0
    private var currentScreen: String = "order_type"
    private val decimalFormat = DecimalFormat("0.00")

    // Customer data fields
    private var customerName: String = ""
    private var customerPhone: String = ""
    private var customerAddress: String = ""
    private var customerEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Show splash screen first
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            showOrderTypeScreen()
        }, 2000)
    }

    private fun showOrderTypeScreen() {
        setContentView(R.layout.activity_order_type)
        currentScreen = "order_type"

        val pickupOption = findViewById<LinearLayout>(R.id.pickupOption)
        val deliveryOption = findViewById<LinearLayout>(R.id.deliveryOption)
        val btnContinue = findViewById<Button>(R.id.btnContinue)

        pickupOption.setOnClickListener {
            orderType = "pickup"
            pickupOption.isSelected = true
            deliveryOption.isSelected = false
        }

        deliveryOption.setOnClickListener {
            orderType = "delivery"
            pickupOption.isSelected = false
            deliveryOption.isSelected = true
        }

        btnContinue.setOnClickListener {
            if (orderType == "delivery") {
                showCustomerInfoScreen()
            } else {
                showMenuScreen()
            }
        }

        // Set initial selection
        pickupOption.isSelected = true
    }

    private fun showCustomerInfoScreen() {
        setContentView(R.layout.activity_customer_info)
        currentScreen = "customer_info"

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val btnContinue = findViewById<Button>(R.id.btnContinueCustomerInfo)

        // Pre-fill with existing data if available
        nameInput.setText(customerName)
        phoneInput.setText(customerPhone)
        addressInput.setText(customerAddress)
        emailInput.setText(customerEmail)

        btnContinue.setOnClickListener {
            // Validate required fields
            if (nameInput.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Bitte geben Sie Ihren Namen ein", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phoneInput.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Bitte geben Sie Ihre Telefonnummer ein", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (orderType == "delivery" && addressInput.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Bitte geben Sie Ihre Adresse für die Lieferung ein", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save customer data
            customerName = nameInput.text.toString().trim()
            customerPhone = phoneInput.text.toString().trim()
            customerAddress = addressInput.text.toString().trim()
            customerEmail = emailInput.text.toString().trim()

            showMenuScreen()
        }
    }

    private fun showMenuScreen() {
        setContentView(R.layout.activity_menu)
        currentScreen = "menu"

        val cartTotalView = findViewById<TextView>(R.id.cartTotal)
        updateCartTotal(cartTotalView)

        setupMenuClickListeners(cartTotalView)

        val cartBar = findViewById<LinearLayout>(R.id.cartBar)
        cartBar.setOnClickListener {
            if (cartTotal > 0) {
                showCartScreen()
            } else {
                Toast.makeText(this, "Ihr Warenkorb ist leer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupMenuClickListeners(cartTotalView: TextView) {
        // Burger items
        findViewById<CardView>(R.id.HamburgerCard).setOnClickListener { addToCart(getString(R.string.namiburger), 9.00, cartTotalView) }
        findViewById<CardView>(R.id.cheeseburgerCard).setOnClickListener { addToCart(getString(R.string.cheeseburger), 9.50, cartTotalView) }
        findViewById<CardView>(R.id.chiliCheeseburgerCard).setOnClickListener { addToCart(getString(R.string.chili_cheeseburger), 10.00, cartTotalView) }
        findViewById<CardView>(R.id.bbqBurgerCard).setOnClickListener { addToCart(getString(R.string.bbq_burger), 10.50, cartTotalView) }
        findViewById<CardView>(R.id.italianJobCard).setOnClickListener { addToCart(getString(R.string.italian_job), 10.50, cartTotalView) }
        findViewById<CardView>(R.id.chickenBurgerCard).setOnClickListener { addToCart(getString(R.string.chicken_burger), 10.50, cartTotalView) }
        findViewById<CardView>(R.id.veggieBurgerCard).setOnClickListener { addToCart(getString(R.string.veggie_burger), 5.50, cartTotalView) }
        findViewById<CardView>(R.id.surfTurfCard).setOnClickListener { addToCart(getString(R.string.surf_turf), 11.50, cartTotalView) }
        findViewById<CardView>(R.id.FrenchBurgerCard).setOnClickListener {addToCart(getString(R.string.French_burger), 11.00, cartTotalView) }
        findViewById<CardView>(R.id.GreekBurgerCard).setOnClickListener {addToCart(getString(R.string.Greek_burger), 11.00, cartTotalView) }
        findViewById<CardView>(R.id.ChickenDeluxeburgerCard).setOnClickListener {addToCart(getString(R.string.Chicken_Deluxe_burger), 11.50, cartTotalView) }
        findViewById<CardView>(R.id.OriginalSmaschedburgerCard).setOnClickListener {addToCart(getString(R.string.Original_Smashed_burger), 11.00, cartTotalView) }








        // Hotdogs
        findViewById<CardView>(R.id.hotdogClassicCard).setOnClickListener { addToCart(getString(R.string.hotdog_classic), 4.00, cartTotalView) }
        findViewById<CardView>(R.id.cheesedogCard).setOnClickListener { addToCart(getString(R.string.cheesedog), 4.50, cartTotalView) }
        findViewById<CardView>(R.id.chilidogCard).setOnClickListener { addToCart(getString(R.string.chilidog), 5.00, cartTotalView) }
        findViewById<CardView>(R.id.bbqDogCard).setOnClickListener { addToCart(getString(R.string.bbq_dog), 5.50, cartTotalView) }

        // Chicken nuggets
        findViewById<CardView>(R.id.nuggets4Card).setOnClickListener { addToCart(getString(R.string.nuggets_4), 3.50, cartTotalView) }
        findViewById<CardView>(R.id.nuggets6Card).setOnClickListener { addToCart(getString(R.string.nuggets_6), 4.50, cartTotalView) }
        findViewById<CardView>(R.id.nuggets9Card).setOnClickListener { addToCart(getString(R.string.nuggets_9), 6.00, cartTotalView) }

        //Salate

        findViewById<CardView>(R.id.GambaSalatCard).setOnClickListener {addToCart(getString(R.string.Gamba_Salat), 16.50, cartTotalView) }
        findViewById<CardView>(R.id.ChickenSalatCard).setOnClickListener {addToCart(getString(R.string.Chicken_Salad), 12.50, cartTotalView) }

    }

    private fun addToCart(itemName: String, price: Double, cartTotalView: TextView) {
        cartTotal += price
        updateCartTotal(cartTotalView)
        Toast.makeText(this, "$itemName zum Warenkorb hinzugefügt!", Toast.LENGTH_SHORT).show()
    }

    private fun updateCartTotal(cartTotalView: TextView) {
        cartTotalView.text = getString(R.string.cart_total, decimalFormat.format(cartTotal))
    }

    private fun showCartScreen() {
        setContentView(R.layout.activity_cart)
        currentScreen = "cart"

        val cartTotalView = findViewById<TextView>(R.id.cartTotal)
        val orderTypeView = findViewById<TextView>(R.id.orderType)
        val customerInfoView = findViewById<TextView>(R.id.customerInfo)
        val btnConfirmOrder = findViewById<Button>(R.id.btnConfirmOrder)
        val btnBackToMenu = findViewById<Button>(R.id.btnBackToMenu)
        val btnEditCustomerInfo = findViewById<Button>(R.id.btnEditCustomerInfo)

        cartTotalView.text = getString(R.string.total_amount, decimalFormat.format(cartTotal))
        orderTypeView.text = getString(R.string.order_type, if (orderType == "pickup") getString(R.string.pickup) else getString(R.string.delivery))

        // Display customer information if available
        if (orderType == "delivery" && customerName.isNotEmpty()) {
            val customerInfoText = "Name: $customerName\nTelefon: $customerPhone\nAdresse: $customerAddress" +
                    if (customerEmail.isNotEmpty()) "\nE-Mail: $customerEmail" else ""
            customerInfoView.text = customerInfoText
            customerInfoView.visibility = TextView.VISIBLE
            btnEditCustomerInfo.visibility = Button.VISIBLE
        } else {
            customerInfoView.visibility = TextView.GONE
            btnEditCustomerInfo.visibility = Button.GONE
        }

        btnConfirmOrder.setOnClickListener {
            // For delivery orders, validate that we have customer info
            if (orderType == "delivery" && (customerName.isEmpty() || customerPhone.isEmpty() || customerAddress.isEmpty())) {
                Toast.makeText(this, "Bitte vervollständigen Sie Ihre Kundendaten", Toast.LENGTH_SHORT).show()
                showCustomerInfoScreen()
                return@setOnClickListener
            }

            val orderConfirmation = if (orderType == "pickup") {
                "Bestellung zur Abholung aufgegeben! Bezahlung erfolgt vor Ort."
            } else {
                "Lieferbestellung aufgegeben! Bezahlung erfolgt bei Lieferung."
            }

            Toast.makeText(this, orderConfirmation, Toast.LENGTH_LONG).show()
            cartTotal = 0.0
            showOrderTypeScreen()
        }

        btnBackToMenu.setOnClickListener {
            showMenuScreen()
        }

        btnEditCustomerInfo.setOnClickListener {
            showCustomerInfoScreen()
        }
    }

    override fun onBackPressed() {
        when (currentScreen) {
            "menu" -> showOrderTypeScreen()
            "cart" -> showMenuScreen()
            "customer_info" -> showOrderTypeScreen()
            else -> super.onBackPressed()
        }
    }
}