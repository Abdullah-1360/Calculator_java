
package calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{
    JFrame frame;
    JButton[] num_but;
    JTextField text;
    double num_1=0,num_2=0;
    char op=' ';

    public Calculator() {
        frame =new JFrame("Calculator");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text =new JTextField();
        text.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(text,BorderLayout.NORTH);
        JPanel button =new JPanel();
        button.setLayout(new GridLayout(7,4));
        String[] buttons={
            "sin","cos","tan","log",
            "!","^","√","π",
            "+","-","*","/",
            "AC","7","8","9",
            "%","4","5","6",
            "DEL","1","2","3",
            ".","00","0","="
        };
        num_but = new JButton[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            num_but[i] = new JButton(buttons[i]);
            num_but[i].addActionListener(this);
            button.add(num_but[i]);
        frame.add(button,BorderLayout.CENTER);
        frame.setVisible(true);
        }
    }
public void actionPerformed(ActionEvent e) {
            JButton button =(JButton) e.getSource();
            String buttonText=button.getText();
        
      if (Character.isDigit(buttonText.charAt(0)) || buttonText.equals(".")) {
            text.setText(text.getText() + buttonText);
        }
      else if(buttonText.equals("=")){
          num_2=Double.parseDouble(text.getText());
          double res=performOperation(num_1,num_2,op);
          text.setText(String.valueOf(res));
          num_1=0;
          num_2=0;
          op=' ';
      }
      else if(buttonText.equals("AC")){
          text.setText(" ");
      } else if (buttonText.equals("sin")) {
            double angle = Double.parseDouble(text.getText());
            double result = Math.sin(Math.toRadians(angle));
            text.setText(String.valueOf(result));
        } else if (buttonText.equals("cos")) {
            double angle = Double.parseDouble(text.getText());
            double result = Math.cos(Math.toRadians(angle));
            text.setText(String.valueOf(result));
        } else if (buttonText.equals("tan")) {
            double angle = Double.parseDouble(text.getText());
            double result = Math.tan(Math.toRadians(angle));
            text.setText(String.valueOf(result));
        
        } else if (buttonText.equals("DEL")) {
            String currentText = text.getText();
            if (!currentText.isEmpty()) {
                text.setText(currentText.substring(0, currentText.length() - 1));
            }
        
        } 
        else if (buttonText.equals("√")) {
            try{
                double num = Double.parseDouble(text.getText());
            
            double result = Math.sqrt(num);
            text.setText(String.valueOf(result));
            }catch(Exception ex){
                text.setText("Expression error ");
            }
        }
        else if (buttonText.equals("%")) {
            try{double num = Double.parseDouble(text.getText());
            double result = num/100;
            text.setText(String.valueOf(result));
}catch(Exception ex){
                text.setText("Expression error ");
            }
        }
        else if (buttonText.equals("π")) {
            try{double num = Double.parseDouble(text.getText());
            double result = num*(3.14159);
            text.setText(String.valueOf(result));
}catch(Exception ex){
                text.setText("Expression error ");
            }
        }
        else if (buttonText.equals("log")) {
            try {
            double num = Double.parseDouble(text.getText());
            double result = Math.log10(num) ;
            text.setText(String.valueOf(result));}
            catch(Exception ex){
                text.setText("Expression error ");
            }
        }
        else if (buttonText.equals("!")) {
            text.setText(String.valueOf(factorial(Double.parseDouble(text.getText()))));
        }
      else{
          op=buttonText.charAt(0);
          num_1=Double.parseDouble(text.getText());
          text.setText("");
      }
}
public  double factorial(double n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
  double performOperation(double operand1, double operand2, char operator) {
        double result = 0;
        switch (operator) {
            case '+' -> result = operand1 + operand2;
            case '-' -> result = operand1 - operand2;
            case '*' -> result = operand1 * operand2;
            case '^' ->{ 
                double op1=operand1;
                for(int i=1;i<operand2;i++){
                    operand1=operand1*op1;
                }                
                result = operand1 ;

            }
            case '/' -> {
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Division by zero");
                }
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
      new Calculator();
    }
}


