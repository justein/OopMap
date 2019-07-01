package com.lyn.nova.algorithm;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/***
 * @ClassName: GoogleTrans
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/6/29 下午10:26
 * @version : V1.0
 */
public class GoogleTrans {

    public static void main(String[] args) {
        // Instantiates a client
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyD-IxQwz1cJjiJEmpkO-gO2kSrIBzIxpkg").build().getService();

        // The text to translate
        String text = "这个服务是白总专用的!";

        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage("zh-CN"),
                        Translate.TranslateOption.targetLanguage("en"));

        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
    }
    }

