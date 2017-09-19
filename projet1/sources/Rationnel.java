public class Rationnel{
    int n=0,a=0,b=0;

    public Rationnel(int n, int a, int b){
	this.n = n;
	if( a < 0 && b < 0){
	    this.a = -a;
	    this.b = -b;
	}else if( a < 0 || b < 0){
	    a = (a<0) ? -a : a;
	    this.b = (b<0) ? -b : b;
	    if( a < b){
		this.n -= 1;
		this.a = this.b-a;
	    }else if(a > b){
		this.n -= a/this.b;
		this.a = a%b;
	    }
	}else{
	    this.b=b;
	    if( a < b){
		this.a = a;
	    }else if(a > b){
		this.n += a/this.b;
		this.a = a%b;
	    }
	}
    }

    public Rationnel(int n){
        this.n = n;
    }

    public Rationnel(int a, int b){
        if( a < 0 && b < 0){
	    this.a = -a;
	    this.b = -b;
	}else if( a < 0 || b < 0){
	    a = (a<0) ? -a : a;
	    this.b = (b<0) ? -b : b;
	    if( a < b){
		this.n -= 1;
		this.a = this.b-a;
	    }else if(a > b){
		this.n -= a/this.b;
		this.a = a%b;
	    }
	}else{
	    this.b=b;
	    if( a < b){
		this.a = a;
	    }else if(a > b){
		this.n += a/this.b;
		this.a = a%b;
	    }
	}
    }

    public boolean estNul(){
	return this.a ==0 && this.b == 0 && this.n == 0;
    }

    public Rationnel inverse(){
	int a = this.b;
	int b = (this.n !=0) ? this.b * this.n + this.a : this.a;
	if(a==0)
	    return new Rationnel(1,this.n);
	return new Rationnel(a, b);
    }

    public Rationnel ajouter(Rationnel r){
	//Manque le cas ou this.n ou r.n est egal a 0
	int den  = this.b * r.b;
	int num = (this.n !=0 && r.n !=0) ? r.b*(this.n*this.b + this.a) + this.b*(r.n*r.b+r.a) : r.b*this.a+this.b*r.b;
	if(this.a == 0 && r.a == 0){
	    return new Rationnel(this.n+r.n);
	}else if(this.a == 0){
	    den = r.b;
	    num = r.b*(this.n+r.n)+r.a;
	}else if(r.a == 0){
	    den = this.b;
	    num = this.b*(this.n+r.n)+this.a;
	}
	return new Rationnel(num, den);
    }

    public Rationnel multiplier(Rationnel r){
	//Manque le cas ou this.n ou r.n est egal a 0
	int den = this.b * r.b;
	int num;
	if(this.n != 0 && r.n !=0)
	    num = this.b*r.b*this.n*r.n + this.b*this.n*r.a + r.b*r.n*this.a+this.a*r.a;
	else
	    num = this.a*r.a;

	if (this.a == 0 && r.a == 0){
	    return new Rationnel(this.n*r.n);
	}else if(this.a == 0){
	    num = this.n*(r.b*r.n+r.a);
	    den = r.b;
	}else if (r.a == 0){
	    num = r.n*(this.b*this.n+this.a);
	    den = this.b;
	}
	return new Rationnel(num, den);
    }

    public int compareTo(Rationnel r){
	if(this.n > r.n)
	    return 1;
	else if(this.n < r.n)
	    return -1;
	else{
	    if(this.a == 0 || r.a == 0)
		return 0;
	    if(this.a*r.b > this.b*r.a)
		return 1;
	    else if(this.a * r.b < this.b * r.a)
		return -1;
	    else return 0;
	}
    }

    public String toString(){
	if(a == 0 && b == 0 && n == 0)
	    return "0";
	if(a==0 || b == 0)
	    return ""+n;
	else if(n==0)
	    return a + "/" + b;
	return n + " + " + a + "/" + b;
    }

    public static void main(String [] args){
        Rationnel r = new Rationnel(1,2,7);
	System.out.println(r.compareTo(new Rationnel(1,2,3)));
	System.out.println(new Rationnel(-2,3));
    }
}
