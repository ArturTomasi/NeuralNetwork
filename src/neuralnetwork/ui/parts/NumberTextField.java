package neuralnetwork.ui.parts;

import javafx.scene.control.TextField;

/**
 *
 * @author artur
 */
public class NumberTextField extends TextField
{
    public void setDouble( double  value )
    {
        formatText( value );
    }
    
    public void setInteger( int value )
    {
        formatText( value );
    }
    
    public void setLong( long value )
    {
        formatText( value );
    }
    
    private void formatText( Object value )
    {
        setText( String.valueOf( value ) );
    }
    
    @Override
    public void replaceText(int start, int end, String text)
    {
        if ( validate( text ) )
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if ( validate( text ) )
        {
            super.replaceSelection( text );
        }
    }

    private boolean validate(String text)
    {
        if( text.isEmpty() )
            return true;
        
        return text.matches( "[0-9.]+" );
    }
   
    
    
    public boolean isValid()
    {
        return ! getDouble().isNaN() &&
               ! getDouble().isInfinite() &&
                 getDouble() != 0d;
    }
    
    
    public Double getDouble()
    {
        double value = Double.NaN;
        
        try
        {
            value =  Double.parseDouble( getText() );
        }
        catch ( Exception e )
        {
            value = 0d;
        }
        
        return value;
    }
    
    public int getInt()
    {
        int value = Integer.MAX_VALUE;
        
        try
        {
            value =  Integer.parseInt( getText() );
        }
        catch ( Exception e )
        {
            value = 0;
        }
        
        return value;
    }
}