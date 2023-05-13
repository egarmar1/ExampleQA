package com.hiberus.university.enrique.maven.first.utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static void clickRandomButtons(int num,List<WebElement> buttons){
        if(num <=0){
            return;
        }
        List<WebElement> randomButtons = new ArrayList<>();
        for (WebElement button : buttons) {
            randomButtons.add(button);
        }
        Collections.shuffle(randomButtons);

        for(int i=0; i<num; i++){
            randomButtons.get(i).click();
        }
    }
}
