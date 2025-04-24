package com.getit.Get.It.utility;

public class Data {

    public static String getMessageBody(String otp , String username) {

        return "<!DOCTYPE html>\n" +
                "<html>\n<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <title>OTP Verification</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }\n" +
                "        .container { max-width: 500px; margin: 50px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }\n" +
                "        h2 { color: #333; }\n" +
                "        p { color: #555; font-size: 16px; }\n" +
                "        .otp { font-size: 24px; font-weight: bold; color: #007BFF; background: #e9ecef; display: inline-block; padding: 10px 20px; border-radius: 4px; margin: 10px 0; }\n" +
                "        .footer { margin-top: 20px; font-size: 14px; color: #777; }\n" +
                "    </style>\n" +
                "</head>\n<body>\n" +
                "    <div class='container'>\n" +
                "        <h2>OTP Verification</h2>\n" +
                "        <p>Hello, " + username + "</p>\n" +
                "        <p>Your One-Time Password (OTP) for verification is:</p>\n" +
                "        <div class='otp'>" + otp + "</div>\n" +
                "        <p>Please enter this OTP to proceed. This code is valid for 10 minutes.</p>\n" +
                "        <p>If you did not request this, please ignore this email.</p>\n" +
                "        <p class='footer'>&copy; 2025 Get It. All rights reserved.</p>\n" +
                "    </div>\n" +
                "</body>\n</html>";

    }
}


//"return <!DOCTYPE html>\n" +
//        "<html>\n<head>\n" +
//        "    <meta charset='UTF-8'>\n" +
//        "    <title>OTP Verification</title>\n" +
//        "    <style>\n" +
//        "        body { font-family: Arial, sans-serif; }\n" +
//        "        .container { text-align: center; padding: 20px; }\n" +
//        "        .otp { font-size: 24px; font-weight: bold; color: #333; }\n" +
//        "    </style>\n" +
//        "</head>\n<body>\n" +
//        "    <div class='container'>\n" +
//        "        <h2>OTP Verification</h2>\n" +
//        "        <p>Your One-Time Password (OTP) for verification is:</p>\n" +
//        "        <div class='otp'>" + otp + "</div>\n" +
//        "        <p>Please enter this OTP to proceed. This code is valid for 10 minutes.</p>\n" +
//        "        <p>If you did not request this, please ignore this email.</p>\n" +
//        "    </div>\n" +
//        "</body>\n</html>";