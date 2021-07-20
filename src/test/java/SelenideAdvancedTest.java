import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.SoftAsserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Screenshots.takeScreenShot;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;
import static org.testng.Assert.assertEquals;

//@Listeners({ ScreenShooter.class})
@Listeners({SoftAsserts.class,ScreenShooter.class })

public class SelenideAdvancedTest {
    public SelenideAdvancedTest(){
        Configuration.startMaximized=true;
        timeout=5000;
        holdBrowserOpen=false;
        reopenBrowserOnFail = true;
        fastSetValue=true;
        assertionMode=AssertionMode.SOFT;

        //Create folder in your src/main/resources directory named 'Reports'
        //- Configure your test to save in case of failure only screenshots (without pagesource)
        reportsFolder="src/main/resources/Reports";
        screenshots=true;
        savePageSource=false;
    }
    @Test
    public void advanced(){

        open( "https://demoqa.com/books" );
        ElementsCollection books = $( ".ReactTable" ).$( ".rt-table" ).find( ".rt-tbody" ).findAll( ".rt-tr-group" ).filterBy( Condition.text( "O'Reilly Media" ) ).filterBy( Condition.text( "JavaScript" ) );
        SoftAssert soft = new SoftAssert();
        soft.assertEquals( books.size(),10);
        //Check that first match row's title equals to 'Learning JavaScript Design Patterns'(success case)
        books.first().shouldHave( Condition.text( "Learning JavaScript Design Patterns" ) );

        //Using stream() click on all matching row's title

        books.stream().forEach(el->{
            actions().moveToElement(el.$(byClassName("mr-2")).$(byTagName("a"))).keyDown(Keys.CONTROL).click().perform();
        });

       //Check with assertion that configuration works properly

        soft.assertEquals(screenshots, true);
        soft.assertEquals(savePageSource, false);

        soft.assertAll();


    }












}
