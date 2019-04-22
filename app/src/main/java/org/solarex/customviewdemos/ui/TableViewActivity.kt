package org.solarex.customviewdemos.ui

import android.os.Bundle
import com.solarexsoft.solarextableview.TableView
import org.solarex.customviewdemos.R


/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 09:57/2019/4/22
 *    Desc:
 * </pre>
 */
 
class TableViewActivity : BaseCustomViewActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_tableview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tableView = findViewById(R.id.table) as TableView
        val matrixTableAdapter = MatrixTableAdapter(this, arrayOf(arrayOf("Header1", "Header2", "Header3", "Header4", "Header5", "Header6", "Header7", "Header8", "Header9", "Header10"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E"), arrayOf("S", "U", "N", "N", "Y", "S", "U", "N", "N", "Y"), arrayOf("S", "O", "L", "A", "R", "S", "O", "L", "A", "R"), arrayOf("T", "A", "B", "L", "E", "T", "A", "B", "L", "E")))
        tableView.setAdapter(matrixTableAdapter)
    }
}