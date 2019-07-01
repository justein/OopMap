package com.lyn.nova.algorithm;

import com.google.cloud.translate.v3beta1.LocationName;
import com.google.cloud.translate.v3beta1.TranslateTextRequest;
import com.google.cloud.translate.v3beta1.TranslateTextResponse;
import com.google.cloud.translate.v3beta1.TranslationServiceClient;
/***
 * @ClassName: GoogleTransV3
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/7/1 下午9:45
 * @version : V1.0
 */
public class GoogleTransV3 {

    /**
     * Translates a given text to a target language.
     *
     * @param projectId - Id of the project.
     * @param location - location name.
     * @param text - Text for translation.
     * @param sourceLanguageCode - Language code of text. e.g. "en"
     * @param targetLanguageCode - Language code for translation. e.g. "sr"
     */
    static TranslateTextResponse translateText(
            String projectId,
            String location,
            String text,
            String sourceLanguageCode,
            String targetLanguageCode) {
        try (TranslationServiceClient translationServiceClient = TranslationServiceClient.create()) {

            LocationName locationName =
                    LocationName.newBuilder().setProject(projectId).setLocation(location).build();

            TranslateTextRequest translateTextRequest =
                    TranslateTextRequest.newBuilder()
                            .setParent(locationName.toString())
                            .setMimeType("text/plain")
                            .setSourceLanguageCode(sourceLanguageCode)
                            .setTargetLanguageCode(targetLanguageCode)
                            .addContents(text)
                            .build();

            // Call the API
            TranslateTextResponse response = translationServiceClient.translateText(translateTextRequest);
            System.out.format(
                    "Translated Text: %s", response.getTranslationsList().get(0).getTranslatedText());
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Couldn't create client.", e);
        }
    }

    public static void main(String[] args) {
        String projectId = "mates-1561602496896";
        String location = "global";
        String sourceLang = "zh-CN";
        String targetLang = "en";
        String text = "这是给白总写的Google翻译的V3测试";
        translateText(projectId, location, text, sourceLang, targetLang);
    }
}
