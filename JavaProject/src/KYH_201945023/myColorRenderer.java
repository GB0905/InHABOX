package KYH_201945023;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class myColorRenderer extends DefaultTableCellRenderer {

 public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,
                                                boolean hasFocus, int row, int column)
 {
 
  Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  
  String s = table.getModel().getValueAt(row, column).toString();
  
  //if구문의 내용은 테이블에 따라 다르니 적절한 코딩하여야 한다.
  //참고로 rowIndex, colIndex는 0부터 시작한다.
  if(s.equalsIgnoreCase("SUCCESS") && column==2)
  {
   comp.setBackground(Color.ORANGE);
   comp.setForeground(Color.red);
  }
  else
  {
   comp.setBackground(new Color(52, 29, 92));
   comp.setForeground(Color.white);
  }
  
  return( comp );
 }
}