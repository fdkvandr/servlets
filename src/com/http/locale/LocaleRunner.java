package com.http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {
        Locale locale = new Locale("ru", "RU");
        System.out.println(Locale.UK);
        System.out.println(Locale.getDefault());

        ResourceBundle translations = ResourceBundle.getBundle("translations", locale); // Без передачи локали будет браться дефолтная
        System.out.println(translations.getString("page.login.password"));
    }

}
