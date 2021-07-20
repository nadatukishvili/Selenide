import com.codeborne.selenide.*;
import com.codeborne.selenide.collections.Texts;
import com.codeborne.selenide.conditions.Attribute;
import com.codeborne.selenide.selector.ByText;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    public SelenideTests(){
        //com.codeborne.selenide.Configuration.browser = "chrome";
        Configuration.startMaximized = true;

    }

    @Test
    public void checkboxes(){

        Selenide.open( "http://the-internet.herokuapp.com/checkboxes ");
        SelenideElement checkbox1=  $((byXpath("//*[@id='checkboxes']/input[1]")));
        checkbox1.click();
        //element( "input" ).setSelected( true );
        $(byXpath("//*[@id='checkboxes']/input[1]")).shouldHave( Condition.attribute("type","checkbox")) ;
        $(byXpath("//*[@id='checkboxes']/input[2]")).shouldHave( Condition.attribute("type","checkbox")) ;
        //sleep(3000);
    }
    @Test
    public void dropdown(){

        Selenide.open( "http://the-internet.herokuapp.com/dropdown");

        SelenideElement option1 = $("#dropdown").$(byText( "Please select an option" ));
        option1.shouldBe( Condition.selected );
        System.out.println(option1.getText());
        SelenideElement option2 = $("#dropdown");
        option2.selectOption( "Option 2" );
        $("#dropdown").$(byValue( "2" )).shouldBe( Condition.selected );
        //System.out.println($("#dropdown").$(byValue( "2" )).shouldBe( Condition.selected ).getText());
        //sleep( 3000 );

    }
    @Test
    public void textBox(){

        Selenide.open( "https://demoqa.com/text-box ");
        $("#userName").setValue( "natia" );
        $(byAttribute( "type", "email" )).setValue( "nadatukishvili@gmail.com" );
        $("#currentAddress-wrapper").$(".form-control").setValue( "sukhishvili" );
        $("#permanentAddress").setValue( "sukhishvili" );
        $("#submit").pressEnter();
        System.out.println($$(".border").get(0).getText());
        $$(".border").shouldHave( texts( "Name:natia" ) );
        $$(".border").shouldHave( texts( "Email:nadatukishvili@gmail.com" ) );
        $$(".border").shouldHave( texts( "Current Address :sukhishvili" ) );
        $$(".border").shouldHave( texts( "Permananet Address :sukhishvili" ) );

        //sleep( 3000 );

    }




}
