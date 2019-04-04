package org.solarex.customviewdemos.ui

import android.content.Context
import android.graphics.*
import android.graphics.PorterDuffXfermode
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:57/2019/4/4
 *    Desc:
 * </pre>
 */

class PorterDuffXfermode : BaseCustomViewActivity() {
    override fun createContentView(): View {
        return XfermodeView(this)
    }

    companion object {

        public fun makeDst(w: Int, h: Int): Bitmap {
            val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            val canvas = Canvas(bm)
            val p = Paint(Paint.ANTI_ALIAS_FLAG)

            p.color = Color.parseColor("#FFFFCC44")
            canvas.drawOval(RectF(0f, 0f, (w * 3 / 4).toFloat(), (h * 3 / 4).toFloat()), p)
            return bm
        }

        public fun makeSrc(w: Int, h: Int): Bitmap {
            val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bm)
            val p = Paint(Paint.ANTI_ALIAS_FLAG)

            p.color = Color.parseColor("#FF66AAFF")
            canvas.drawRect((w / 3).toFloat(), (h / 3).toFloat(), (w * 19 / 20).toFloat(), (h * 19 / 20).toFloat(), p)
            return bm
        }

        class XfermodeView(context: Context): View(context) {
            companion object {
                const val W = 64;
                const val H = 64;
                const val ROW_MAX = 4;

                val sModes = arrayOf(
                        PorterDuffXfermode(PorterDuff.Mode.CLEAR),
                        PorterDuffXfermode(PorterDuff.Mode.SRC),
                        PorterDuffXfermode(PorterDuff.Mode.DST),
                        PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
                        PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
                        PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
                        PorterDuffXfermode(PorterDuff.Mode.DST_IN),
                        PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
                        PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
                        PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
                        PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
                        PorterDuffXfermode(PorterDuff.Mode.XOR),
                        PorterDuffXfermode(PorterDuff.Mode.DARKEN),
                        PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
                        PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
                        PorterDuffXfermode(PorterDuff.Mode.SCREEN)
                )

                val sLabels = arrayOf(
                        "clear",
                        "src",
                        "dst",
                        "src_over",
                        "dst_over",
                        "src_in",
                        "dst_in",
                        "src_out",
                        "dst_out",
                        "src_atop",
                        "dst_atop",
                        "xor",
                        "darken",
                        "lighten",
                        "multiply",
                        "screen"
                )
            }

            lateinit var mSrcB: Bitmap;
            lateinit var mDstB: Bitmap;
            lateinit var mBG: Shader;

            init {
                mSrcB = makeSrc(W, H)
                mDstB = makeDst(W, H)
                val bm = Bitmap.createBitmap(intArrayOf((0xFFFFFFFF).toInt(), (0xFFCCCCCC).toInt(),
                        (0xFFCCCCCC).toInt(), (0xFFFFFFFF).toInt()), 2, 2, Bitmap.Config.RGB_565)
                mBG = BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
                val matrix = Matrix()
                matrix.setScale(6f, 6f)
                mBG.setLocalMatrix(matrix)
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onDraw(canvas: Canvas) {
                canvas.drawColor(Color.WHITE)
                val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                labelPaint.textAlign = Paint.Align.CENTER

                val paint = Paint()
                paint.isFilterBitmap = false

                canvas.translate(15f, 35f)
                var x = 0
                var y = 0
                for (i in 0 until sModes.size) {
                    paint.style = Paint.Style.STROKE
                    paint.shader = null
                    canvas.drawRect(x-0.5f, y-0.5f, x+W+0.5f, y+H+0.5f, paint)

                    paint.style = Paint.Style.FILL
                    paint.shader = mBG
                    canvas.drawRect(x.toFloat(), y.toFloat(), (x+W).toFloat(), (y+H).toFloat(), paint)

                    val newPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                    val sc = canvas.saveLayer(x.toFloat(), y.toFloat(), (x+W).toFloat(), (y+H).toFloat(), null)
                    canvas.translate(x.toFloat(), y.toFloat())
                    canvas.drawBitmap(mDstB, 0f, 0f, newPaint)
                    newPaint.xfermode = sModes[i]
                    canvas.drawBitmap(mSrcB, 0f, 0f, newPaint)
                    newPaint.xfermode = null
                    canvas.restoreToCount(sc)

                    canvas.drawText(sLabels[i], (x+W/2).toFloat(), (y-labelPaint.textSize/2).toFloat(), labelPaint)

                    x += W+10

                    if ((i % ROW_MAX) == ROW_MAX - 1) {
                        x = 0
                        y += H + 30
                    }
                }
            }
        }
    }

}