import java.util.Arrays;
import java.util.*;
public class Polynomial implements Comparable <Polynomial> 
{
    private int[] coefficients;
    private int degree;
    
    public Polynomial(int [] coefficients)
    {
     this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
     
    }
    
    public int getDegree()
    {
        for (int i = coefficients.length -1; i>=0; i--)
        {
            if(coefficients[i] != 0)
            {
                degree = i;
                break;
            }
            else
            {
                continue;
            }
        }
        return degree;
        
        } 
    
    @Override public String toString()
    {
       String res = "";
       
       for (int i = 0; i < coefficients.length-1; i++)
       {
           if (i == 0)
           {
               res +=  "(" + coefficients[i] + ") + " ;
           }
           else if (coefficients[i] == 0)
           {
               continue;
           }
           else
           {
               res += "(" + coefficients[i] + ")" + "x^" + (i) + "+";
            }
       }
       
       return res;
       
    }
    
    
    public int getCoefficient(int k)
    {
        if (k > coefficients.length-1)
        {
            return 0;
        }
        else
        {
            return coefficients[k];
        }
    }
    
    public long evaluate(int x)
    {
        long res = 0;
        
        for(int i = coefficients.length-1; i >= 0; i--)
        {
            res = res + (coefficients[i] * (long)Math.pow(x, i));
        }
        return res;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Polynomial)) {
            return false;
        }
        
        if(this.compareTo((Polynomial)other) == 0)
        {
            return true;
        }
        return false;
        
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        
        for (int i =0; i<getDegree(); i++)
        {
            result = prime*result+ coefficients[i];
        }
        
        return result;
        
        
    }
    
    public int compareTo(Polynomial other)
    {
        
        TreeSet<Polynomial> ts = new TreeSet<Polynomial>();
    
        if(this.getDegree() > other.getDegree())
        {
            return 1;
        }
        
        if(this.getDegree() < other.getDegree())
        {
            return -1;
        }
        
        if(this.getDegree() == other.getDegree())
        {
            for(int i = this.coefficients.length-1; i >= 0; i--)
            {
                if(this.getCoefficient(i) > other.getCoefficient(i))
                {
                    return 1;
                }
                else if (this.getCoefficient(i) < other.getCoefficient(i))
                {
                    return -1;
                }
            }
        }
        return 0;
        
    }
    
    public Polynomial add(Polynomial other)
    {        
        int max = Math.max(this.getDegree(), other.getDegree());
        int[] res = new int[max+1];
        
        for(int i =0; i<=this.getDegree(); i++)
        {
            res[i] += this.coefficients[i];
        }
        
        for (int i = 0; i<= other.getDegree(); i++)
        {
            res[i] += other.coefficients[i];
        }
        
        
        Polynomial result = new Polynomial(res);
        return result;
    }
    
    public Polynomial multiply(Polynomial other)
    {
        int max = this.getDegree() + other.getDegree();
        int[] co = new int [max+1];
        
        for (int i = 0; i <= this.degree; i++) {
            for(int j = 0; j<=other.getDegree(); j++) {
                co[i + j] += coefficients[i]* other.getCoefficient(j);
            }
        }
        Polynomial result = new Polynomial(co);
        return result;
        
    }
  
}
