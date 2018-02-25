package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

//���ƴ���
public abstract class Layer {
	// �ڱ߾�
	protected static final int PADDING;
	// �߿���
	protected static final int BORDER;

	int WINDOW_IMGW = Img.WINDOW_IMG.getWidth(null);
	int WINDOW_IMGH = Img.WINDOW_IMG.getHeight(null);

	protected static final int IMG_NUMBER_W = Img.IMG_NUMBER.getWidth(null) / 10;
	private static final int IMG_NUMBER_H = Img.IMG_NUMBER.getHeight(null);

	protected static final int RECT_H = Img.RECT_IMG.getHeight(null);
	// ֵ��ͼƬ���
	private static final int RECT_W = Img.RECT_IMG.getWidth(null);
	// ֵ�ۿ��
	private final int rectW;
	// Ĭ����Ʒ
	private static final Font DEF_FONT = new Font("����", Font.BOLD, 20);
	// �������Ͻ�x����
	protected int x;
	// �������Ͻ�y����
	protected int y;
	// ���ڿ��
	protected int w;
	// ���ڸ߶�
	protected int h;
	protected GameDto dto = null;
	static {
		FrameConfig fCfg = GameConfig.getFrameConfig();
		PADDING = fCfg.getPadding();
		BORDER = fCfg.getBorder();
	}

	protected Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rectW = this.w - (PADDING << 1);
	}
   /**
    * ������Ϸ������
    * @param dto
    */
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
    /**
     * �����ڲ�����
     * @param g
     */
	protected void createWindow(Graphics g) {
		// ����
		g.drawImage(Img.WINDOW_IMG, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER, null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + BORDER, y, x + w - BORDER, y + BORDER, BORDER, 0, WINDOW_IMGW - BORDER, BORDER,
				null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + w - BORDER, y, x + w, y + BORDER, WINDOW_IMGW - BORDER, 0, WINDOW_IMGW, BORDER,
				null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x, y + BORDER, x + BORDER, y + h - BORDER, 0, BORDER, BORDER, WINDOW_IMGH - BORDER,
				null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + BORDER, y + BORDER, x + w - BORDER, y + h - BORDER, BORDER, BORDER,
				WINDOW_IMGW - BORDER, WINDOW_IMGH - BORDER, null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + w - BORDER, y + BORDER, x + w, y + h - BORDER, WINDOW_IMGW - BORDER, BORDER,
				WINDOW_IMGW, WINDOW_IMGH - BORDER, null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x, y + h - BORDER, x + BORDER, y + h, 0, WINDOW_IMGH - BORDER, BORDER, WINDOW_IMGH,
				null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + BORDER, y + h - BORDER, x + w - BORDER, y + h, BORDER, WINDOW_IMGH - BORDER,
				WINDOW_IMGW - BORDER, WINDOW_IMGH, null);
		// ����
		g.drawImage(Img.WINDOW_IMG, x + w - BORDER, y + h - BORDER, x + w, y + h, WINDOW_IMGW - BORDER,
				WINDOW_IMGH - BORDER, WINDOW_IMGW, WINDOW_IMGH, null);

	}

	/** 
	 * ��������
	 * x���Ͻ����� y���Ͻ����� num Ҫ��ʾ������ g ���ʶ���
	 */
	protected void drawNumber(int x, int y, int num, Graphics g) {
		String strNum = Integer.toString(num);
		for (int i = 0; i < strNum.length(); i++) {
			int bit = strNum.charAt(i) - '0';
			g.drawImage(Img.IMG_NUMBER, this.x + x + IMG_NUMBER_W * i, this.y + y, this.x + x + IMG_NUMBER_W * (i + 1),
					this.y + y + IMG_NUMBER_H, bit * IMG_NUMBER_W, 0, (bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
		}

	}
/**
 * ���Ʋ�ֵ		
 * @param y
 * @param titl
 * @param number
 * @param percentage
 * @param g
 */
	protected void drawRect(int y, String titl, String number, double percentage, Graphics g) {
		// ����ֵ��ʼ��
		int rect_X = this.x + PADDING;
		int rect_Y = this.y + y;

		g.setColor(Color.BLACK);
		g.fillRect(rect_X, rect_Y, this.rectW, RECT_H + 4);
		g.setColor(Color.white);
		g.fillRect(rect_X + 1, rect_Y + 1, this.rectW - 2, RECT_H + 2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_X + 2, rect_Y + 2, this.rectW - 4, RECT_H);
		// ���Ʋ�ֵ
		// ������
		int w = (int) ((percentage) * (this.rectW - 4));
		// �����ɫ
		int subIdx = (int) (percentage * RECT_W) - 1;
		g.drawImage(Img.RECT_IMG, rect_X + 2, rect_Y + 2, rect_X + 2 + w, rect_Y + 2 + RECT_H, subIdx, 0, subIdx + 1,
				RECT_H, null);
		// ��������
		g.setColor(Color.white);
		g.setFont(DEF_FONT);
		g.drawString(titl, rect_X + 4, rect_Y + 22);
		if (number != null) {
			g.drawString(number, rect_X + 250, rect_Y + 22);
		}

	}
	/**
	 * ��ͼƬ������������
	 * @param g
	 * @param img
	 */
	protected void drawImageAtCenter(Graphics g, Image img) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		g.drawImage(img, this.x + (this.w - imgW >> 1), this.y + (this.h - imgH >> 1), null);
	}

	public abstract void paint(Graphics g);
}
