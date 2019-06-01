package jp.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * QRコード生成
 *
 */
public class QRMain {

	private static final String OUTPUTDIR = "out\\";

	public static void main(String[] args) throws Exception {
		// QRCodeに変換する対象
		String target = "sampleCode";
		String encoding = "UTF-8";
		int size = 100;
		String path = "QR.png";

		ConcurrentHashMap map = new ConcurrentHashMap<>();
		// 設定
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		map.put(EncodeHintType.CHARACTER_SET, encoding);
		map.put(EncodeHintType.MARGIN, 0);
		QRCodeWriter writer = new QRCodeWriter();
		// 項目を設定
		BitMatrix matrix = writer.encode(target, BarcodeFormat.QR_CODE, size, size, map);

		// 出力
		BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

		// ファイル出力
		ImageIO.write(image, "png", new File(OUTPUTDIR, path));
	}
}
