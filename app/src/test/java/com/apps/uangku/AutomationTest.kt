package com.apps.uangku

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.time.Duration

class AutomationTest {
    private lateinit var driver: AndroidDriver
    private lateinit var wait: WebDriverWait

    @BeforeEach
    fun setUp() {
        val options = UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("vivo vivo 1902")
            .setAppPackage("com.apps.uangku")
            .setAppActivity("com.apps.uangku.MainActivity") // Gunakan FQN (Full Qualified Name)
            .setAutomationName("UiAutomator2")
            .setNoReset(true)

        driver = AndroidDriver(URL("http://127.0.0.1:4723"), options)

        wait = WebDriverWait(driver, Duration.ofSeconds(10))
    }

    @Test
    fun testMunculkanBottomSheetLewatFAB() {
        val FabAdd = wait.until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("FabAdd"))
        )
        FabAdd.click()

        Thread.sleep(1500)

        driver.pressKey(KeyEvent(AndroidKey.BACK))

        val fabTampilLagi = wait.until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("FabAdd"))
        )

        assertTrue(
            fabTampilLagi.isDisplayed,
            "FAB Terlihat kembali setelah sheet ditutup"
        )
    }

    @Test
    fun switchTestForTransactionTab() {
        // Klik Tab Expenses dan cek apakah Netflix muncul
        val expensesTab = wait.until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("TransactionTabExpenses"))
        )
        expensesTab.click()

        val itemNetflix = wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("TransactionItemNetflix"))
        )
        assertTrue(itemNetflix.isDisplayed, "Gagal: Daftar transaksi Expenses tidak muncul")

        // Klik Tab Income dan cek apakah Salary muncul
        val incomeTab = wait.until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("TransactionTabIncome"))
        )
        incomeTab.click()

        val itemSalary = wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("TransactionItemSalary"))
        )
        assertTrue(itemSalary.isDisplayed, "Gagal: Daftar transaksi Income tidak muncul")

        // Klik Tab Savings dan cek apakah Emergency Fund muncul
        val savingsTab = wait.until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("TransactionTabSavings"))
        )
        savingsTab.click()

        val itemEmergency = wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("TransactionItemEmergency Fund"))
        )
        assertTrue(itemEmergency.isDisplayed, "Gagal: Daftar transaksi Savings tidak muncul")
    }

    @AfterEach
    fun tearDown() {
        if(::driver.isInitialized) {
            driver.quit()
        }
    }
}