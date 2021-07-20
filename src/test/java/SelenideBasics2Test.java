import com.codeborne.selenide.*;

import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.testng.SoftAsserts;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

@Listeners({SoftAsserts.class})

public class SelenideBasics2Test {
    @Test
    public void chainLocators() {
        //SoftAsserts soft = new SoftAsserts();
        Configuration.assertionMode = SOFT;
        Configuration.startMaximized = true;
        Selenide.open( "https://demoqa.com/books " );
        ElementsCollection books = $( ".ReactTable" ).$( ".rt-table" ).$( ".rt-tbody" ).$$( ".rt-tr-group" );
        books.filterBy( Condition.text( "O'Reilly Media" ) ).filterBy( Condition.text( "JavaScript" ) ).texts() ;
        //books.filterBy( Condition.text( "O'Reilly Media" ) ).filterBy( Condition.text( "JavaScript" ) ).shouldHave( CollectionCondition.size( 10 ) );
        Assert.assertEquals( books.filterBy( Condition.text( "O'Reilly Media" ) ).filterBy( Condition.text( "JavaScript" ) ).size(),10 );

        //ამოხსნა არის , მაგრამ პირობას ვერ აკმაყოფილებს
       $(".rt-tbody").findAll(".rt-tr-group").stream().forEach(el ->{
            el.findAll(byAttribute("alt", "image")).stream().forEach(element ->{
                element.isImage();
           });
       });






    }
}

