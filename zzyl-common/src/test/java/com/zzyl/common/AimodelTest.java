package com.zzyl.common;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class AimodelTest {
    public static void main(String[] args) {
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("bce-v3/ALTAK-3Nt2UUmbdknbDqV5MeQhw/60a6e03d08641e70c0536265e6a032637eab0308") //将your_APIKey替换为真实值，如何获取API Key请查看：https://console.bce.baidu.com/iam/#/iam/apikey/list
                .baseUrl("https://qianfan.baidubce.com/v2/") //千帆ModelBuilder平台地址
                .build();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage("你好") // 对话messages信息
                .model("ernie-x1-turbo-32k") // 模型对应的model值，请查看支持的模型列表：https://cloud.baidu.com/doc/qianfan-docs/s/7m95lyy43
                .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        System.out.println(chatCompletion.choices().get(0).message().content());
    }
}
