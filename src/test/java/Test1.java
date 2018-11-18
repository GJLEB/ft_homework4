import org.junit.Test;

import org.openqa.selenium.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.GoogleMain;
import pages.GoogleResult;
import pages.TinkoffJob;
import pages.TinkoffMobile;






public class Test1 extends BaseRunner {
    private Logger logger = LoggerFactory.getLogger(Test1.class);


    @Test
    public void Test0() {
        TinkoffJob tinkoffJob = app.tinkoffJob;
        tinkoffJob.getPage("https://moscow-job.tinkoff.ru/");
        tinkoffJob.typeForm("", "", "", "");
        tinkoffJob.errorsCheck();

    }

    @Test
    public void Test2() {
        GoogleMain googleMain = app.google;
        TinkoffJob tinkoffJob = app.tinkoffJob;
        googleMain.open();
        googleMain.FindinRequsetList("мобайл тинькофф", "тинькофф мобайл тарифы");
        GoogleResult googleResult = app.googleResults;
        googleResult.clickSearchResultsByLinkContains("https://www.tinkoff.ru/mobile-operator/tariffs/");
        googleResult.switchToWindow("Тарифы Тинькофф Мобайл");
        tinkoffJob.isLoadedByTitleContains("Тарифы Тинькофф Мобайл");
        tinkoffJob.switchToMainTab();
        googleMain.closeCurrentTab();
        googleResult.switchToWindow("Тарифы Тинькофф Мобайл");
        googleMain.CheckCurrentUrl("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }


    @Test
    public void test3() {
        TinkoffMobile tinkoffMobile = app.tinkoffMobile;
        tinkoffMobile.getPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
        tinkoffMobile.clickonElementbyText("Нет");
        tinkoffMobile.clickonElementbyText("Москва и Московская о.");
        tinkoffMobile.checkRegion("Москва");
        tinkoffMobile.refreshpage();
        tinkoffMobile.checkRegion("Москва");

        int defMsk = tinkoffMobile.getPrice();

        Select select = new Select();
        select.set("Интернет", "Безлимитный интернет", tinkoffMobile.returndriver());
        select.set("Звонки", "Безлимитные минуты", tinkoffMobile.returndriver());
        CheckBox checkBox = new CheckBox();
        checkBox.click("Режим модема", tinkoffMobile.returndriver());
        checkBox.click("Безлимитные SMS", tinkoffMobile.returndriver());
        int maxMsk = tinkoffMobile.getPrice();

        tinkoffMobile.clickonElementbyText("Москва");
        tinkoffMobile.clickonElementbyText("Краснодар");


        int defKrsn = tinkoffMobile.getPrice();

        select.set("Интернет", "Безлимитный интернет", tinkoffMobile.returndriver());
        select.set("Звонки", "Безлимитные минуты", tinkoffMobile.returndriver());
        checkBox.click("Режим модема", tinkoffMobile.returndriver());
        checkBox.click("Безлимитные SMS", tinkoffMobile.returndriver());

        int maxKrsn = tinkoffMobile.getPrice();

        if (defKrsn == defMsk) {
            throw new InvalidArgumentException("Дефолтные цены в Краснодаре и Москве одинаковые");
        }
        if (maxKrsn != maxMsk) throw new InvalidArgumentException("Максимальные цены в Краснодаре и Москве разные");


    }



    @Test
    public void test4() {
        TinkoffMobile tinkoffMobile = app.tinkoffMobile;
        tinkoffMobile.getPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
        Select select = new Select();
        CheckBox checkBox = new CheckBox();
        Button button = new Button();
        select.set("Интернет","0 ГБ",tinkoffMobile.returndriver());
        select.set("Звонки","0 минут",tinkoffMobile.returndriver());
        checkBox.click("Мессенджеры",tinkoffMobile.returndriver());
        checkBox.click("Социальные сети",tinkoffMobile.returndriver());
        int price=tinkoffMobile.getPrice();
        if (price!=0) throw new InvalidArgumentException("Цена не равна нулю");
        if(button.isAvalibale(tinkoffMobile.returndriver())) {
            logger.info("Кнопка активна при, отсутствии выбранных опций.");
            throw new IllegalArgumentException("Кнопка активна при, отсутствии выбранных опций.");
        }




    }
}


