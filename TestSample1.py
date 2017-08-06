from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# Create a new session

driver = webdriver.Safari()
driver.implicitly_wait(10)
driver.maximize_window()


# Navigate N11.com

driver.get("http://www.n11.com")

#driver.get("file:///Users/kadirby/Documents/WebTestAutomation/src/N11.html")


# Navigate "Üye Girisi"

driver.find_element_by_xpath("//div[@class='loginStatus']/a[@class='btnSignIn']").click()




# Navigate "Facebook Girisi"

#driver.get(Loginlink.get_attribute("href"))

Facebooklink = driver.find_element_by_xpath("//div[@class='button inicon facebook medium facebookBtn  btnLogin']")

print(Facebooklink.get_attribute("data-connecturl"))

driver.get(Facebooklink.get_attribute("data-connecturl"))

# Set Facebook User-Pass & Login

username = driver.find_element_by_id("email")
password = driver.find_element_by_id("pass")

username.send_keys("test@test.com")
password.send_keys("test123*")

driver.find_element_by_name("login").click()

# Find Search Area

productSearchForm=driver.find_element_by_id("searchData")

# Make Search

productSearchForm.clear()
productSearchForm.send_keys("Iphone 7")
productSearchForm.send_keys(Keys.RETURN)

# Get First Link Contents

FirstLinkPath = driver.find_element_by_xpath("//section/div/ul/li/div/div/a")

FirstLinkProductName = driver.find_element_by_xpath("//div/a/h3[@itemprop='name']").text

FirstLinkProductPrice = driver.find_element_by_xpath("//div[@itemprop='offers']/a/ins[@itemprop='price']").text

FirstLink = FirstLinkPath.get_attribute("href")


print(FirstLinkProductName)

print(FirstLinkProductPrice)

print(FirstLink)

# Get First Link Detail Page

driver.get(FirstLink)

# Get Detail Link Contents

DetailLinkProductName = driver.find_element_by_xpath("//div[@class='proDetail']/div/div/h1[@class='proName']").text

DetailLinkProductPrice = driver.find_element_by_xpath("//ins[@itemprop='price']").text

print(DetailLinkProductName)

print(DetailLinkProductPrice)


# Check First Link & Detail Link Contents

if DetailLinkProductName.strip() == FirstLinkProductName.strip():
    if DetailLinkProductPrice == FirstLinkProductPrice:
        print("Test Başarılı")
        driver.get_screenshot_as_file('/Users/kadirby/Documents/WebTestAutomation/src/n11.png')
    else:
        print("Test Başarısız - Ürünler Farklı")
else:
    print("Test Başarısız - Ürünler Farklı")


# Log Out

driver.get(driver.find_element_by_xpath("//a[@class='logoutBtn']").get_attribute("href"))

# Close Browser

driver.quit()






