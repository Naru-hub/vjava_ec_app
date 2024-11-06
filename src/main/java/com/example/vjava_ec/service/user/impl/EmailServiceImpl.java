package com.example.vjava_ec.service.user.impl;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.service.user.EmailService;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

/**
 * EmailServiceインターフェースの実装クラス
 * メールの送信をする
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	// DI
	private final JavaMailSender mailSender;	

	/**
	 * 購入確認メールを送る
	 * @param order
	 * @param user
	 */
	@Override
	public void sendThanksEmail(Order order,User user) {
		// メールの構築と送信
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("vjavaec@gmail.com");
            helper.setSubject("VJavaECでのご注文");
            String message = user.getName() + "様\nこの度VJavaECのご利用ありがとうございました。"
                    + "詳細は注文履歴からご確認ください。\n\n"
                    + "注文の確認\n"
                    + "お届け先：\n"
                    + order.getDeliveryName() + "\n"
                    + order.getDeliveryPostcode() + "\n"
                    + order.getDeliveryAddress() + "\n"
                    + order.getDeliveryTel() + "\n\n"
                    + "合計金額：" + order.getTotalPrice() + "円";
            helper.setText(message, false); // HTMLではないプレーンテキストとして送信
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // ログを出力するか、例外をハンドリングします
            e.printStackTrace();
        }
	}

	/**
	 * 新規登録のためのコード生成とメールの送信
	 * @param mailAddress
	 * @return int 生成した認証コード
	 */
	@Override
	public int sendConfirmEmail(String mailAddress) {
		Random rand = new Random();
		// コードの生成
		int code = 100000 +  rand.nextInt(900000);
		// メールの構築と送信
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(mailAddress);
            helper.setFrom("vjavaec@gmail.com");
            helper.setSubject("VJavaEC:新規登録のメール認証");
            String message = "認証コードは　" + code + "　です\n"
            		+ "コードの有効時間は３分です";
            helper.setText(message, false); // HTMLではないプレーンテキストとして送信
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // ログを出力するか、例外をハンドリングします
            e.printStackTrace();
        }
		return code;
	}

	/**
	 * パスワード再設定のメールを送る
	 * @param emailAddress
	 * @return String パスワード再設定トークン
	 */
	@Override
	public String sendResetPasswordEmail(String emailAddress) {
		SecureRandom secureRandom = new SecureRandom();
		// 32バイトのトークンを生成
		byte[] randomBytes = new byte[32]; 
		secureRandom.nextBytes(randomBytes);
		// トークンを16進数文字列に変換して使用
		StringBuilder token = new StringBuilder();
		for (byte b : randomBytes) {
		    token.append(String.format("%02x", b));
		}
		String resetToken = token.toString();
		// メールの構築と送信
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(emailAddress);
            helper.setFrom("vjavaec@gmail.com");
            helper.setSubject("VJavaEC:パスワードの再設定");
            String message = "パスワードの再設定\n\n"
            		+ "下記のリンクからパスワードの再設定をしてください\n\n"
            		+ "http://localhost:8080/user/login/reset/password?token=" + resetToken + "\n\n"
            		+ "有効期間は五分です";
            helper.setText(message, false); // HTMLではないプレーンテキストとして送信
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // ログを出力するか、例外をハンドリングします
            e.printStackTrace();
        }
		return resetToken;
	}
}
