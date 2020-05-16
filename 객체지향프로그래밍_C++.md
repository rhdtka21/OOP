### The primary idea behind the Object Oriented Programming(OOP) is to join data and function

- Example  

  Describe Circle -> 중심의 좌표, 반지름

  * C

    ```c
    //Data
    typedef struct CIRCLE{
    	float x, y, radius;
    }Circle;
    
    //Function
    float circleArea(Circle c1){
    	return c1.radius*c1.radius*3.14159;
    }
    ```

    No Strong connection between this Data and Function

  * C++

    ```c++
    class Circle{
    public:
    	float x;
    	float y;
    	float radius;
    	
    	float Circle::getArea(){
    		return radius*radius*3.14159;
    	}
    };
    ```

    A class brings together data and function.

### Ideas behind OOP

1. Join Data and Function
2. inheritance(상속성)
3. encapsulation
4. polymorphism(다형성)

### Inheritance (상속)

Inheritance creates a parent - child relationship between two classes

The child has all the data and function of the parent, plus some additional data and function

- Parent - Shape 			

  || Shape Data : color of the shape, function : get_color

- Child - Circle, Square  

  || Circle Data : x, y, radius, Square Data : x, y, side_length

  || Circle Function : get_area, Square Function : get_area

Circle and Square is the child of the Parent, so they both have the data; color.

A circle has x, y, radius, color and a square has x, y, side_length, color

Circles and Squares can perform the get_color function.



### Encapsulation 

Some data in a class should not be accessed(read, changed) by any function outside of the class itself

Data in a class can be labeled as "private" This will prevent any function outside the class from accessing or changing the data.

```c++
class Date{
private:
	int year, month, day;
}
```

private : only a function in the Date Class can access or change this data

The Writer of the Date class knows month should not be > 12. Therefore, month has been labeled "Private" to prevent an outside function from changing month to an invalid number.

Abstraction is closely related to encapsulation. Abstraction means that an outside function writer does not want to know about the data or function in a class



### Polymorphism (다형성)

A variable in a program can change its class while the program is running. Usually it changes to a subclass.

```c++
Shape * s1;

if(조건){
	s1 = new Circle;
}
else{
	s1 = new Triangle;
}

s1->getArea();
}
```

In this example the `getArea()` function could be in the Triangle class or in the Circle class.



### Let's make a shape class in c++ using encapsulation

- `shape.h`

  ```
  class Shape{
  private:
  	int color;
  public:
  	int getColor();
  	void setColor(int newColor)
  };
  ```

  A common idea in OOP is to make data private and functions public.

  Data is read with functions called "getters"

  - Example : `getColor()`

  Data is changed with functions called "setters"

  - Example : `setColor()`

  Getters and Setters are used to enforce **Validation**
  
  

### Validation(유효성)

Validation is the idea that data should never be in an "invalid" or impossible state.

- Example

  ```c++
  void Circle::setRadius(float newRadius){
  	if(newRadius < 0){
  		cout<<"Error:Radius<0";
  	}
      else{
          radius = newRadius;
      }
  }
  ```

  > ### C++ is the Superset of C
  >
  > Anything which you could do in C can also be done in C++
  >
  > `printf()` is available in C++ but `cout` should be used instead.
  >
  > `cout` is OOP while `printf()`is not.

### Instance

C++ Programs begin with a `main()` function just like C.

Instances of classes are like variables of a type int or float.

- `CircleProgram.cpp`

  ```c++
  #include <iostream>
  using namespace std;
  
  int main(){
      float radius, area;
      Circle myCircle;
      
      cout << "What is the radius?";
      cin >> radius;
      
      myCircle.setRadius(radius);
      area = myCircle.getArea();
      
      cout << "The Area is " << area << endl;
          
  }
  ```

  `main()` cannot see the `Circle.radius`

- `Circle.h`

  ```c++
  class Circle{
  private:
  	float radius;
  public:
  	float getRadius();
  	float getArea();
  	void setRadius(float newRadius);
  };
  ```

- `Circle.cpp`

  ```c++
  float Circle::getRadius(){
  	return radius;
  }
  float Circle::getArea(){
  	return radius * radius * 3.14159;
  }
  void Circle::setRadius(float newRadius){
  	radius = newRadius;
  }
  ```


### Constructor (생성자)

When a circle is created what should the circle look like? If we have a class Circle I can create this circle.

![KakaoTalk_20190919_201954453](https://user-images.githubusercontent.com/59441164/82115710-811d5980-979f-11ea-8d4e-55e915f0715a.jpg)

```c++
int main(){
    Circle cir1;			//create the instance of the circle
//What does the Circle look like before set the X,Y,Radius?
    cir1.setX(2);
    cir1.setY(2);
    cir1.setRadius(1);
//After setters, x=2, y=2, radius=1
}

class Circle{
    float x,y,radius;
    
    void setX(float newX);
    void setY(float newY);
    void setRadius(float newRadius);

}

```

When a instance of a Circle is created, memory is allocated for the data inside the circle. 

Memory allocation for a circle.

![KakaoTalk_20190919_202114563](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\KakaoTalk_20190919_202114563.jpg)

What does this memory contain?

After `Circle cir1`, memory contains garbage numbers.

After `cir1.setX(2);`, memory contains garbage numbers except x.

After `cir1.setY(2);` `cir1.setRadius(1);` _image 2_

The purpose of a constructor is  to give starting data (initial data) to an instance when the instance is crated.

The constructor is a function which is run as soon as an instance is created.

A constructor in `C++` has the same name as the class and no return value.

```c++
Circle::Circle(){
	x = 0;
	y = 0;
	radius = 1;
//Every Circle starts with x=0, y=0, radius=1
//Any change in the setter must also be changed in the constructor
}
```

If the class has setters it is better to call the setters inside the constructors.

```c++
Circle::Circle(){
	setX(0);
	setY(0);
	setRadius(1);
//Every Circle starts with x=0, y=0, radius=1
//Only change the setter
}
```

### Destructor (소멸자)

```c++
void useCircle(){
    Circle c1;		//memory is created for a circle
    .
    .
    .
    return;			//memory storing the circle is destroyed
}
```

A destructor can be used to destroy the memory which had been used by the instance . However destructor is often not necessary because the instance is automatically destroyed at the end of the function where the instance was created.

We only need a destructor if the constructor created ***additional memory*** for the instance.

A destructor is a function with the same name as the class with `~` at the beginning.

```c++
class Student{
	int ID;
	char * name;
    .
    .
    .

}

Student::Student(){
    ID = 0;
    name = malloc(8 * sizeof(char));	//extra memory allocation
    strcpy(name, "Unknown");			//name에 8개의 char를 할당후 strcpy함.
}

Student::~Student(){
    free(name);				//destroy extra memory created in the constructor
}
```

In `C++` do not use `malloc` and `free` (usually in `C`)

In `C++` we can us `new` and `delete` toe create and destroy memory.

```c++
class Student{
	int ID;
	char * name;
    .
    .
    .

};

Student::Student(){
    ID = 0;
    name = new char[8];				//extra memory allocation
    strcpy(name, "Unknown");			//name에 8개의 char를 할당후 strcpy함.
}

Student::~Student(){
    delete[] name;				//destroy extra memory created in the constructor
}
```

A constructors can have default parameters. May want to say `Circle cir(2,3,1);` 

This command need a constructor which can accept 3 parameters.

```c++
Circle::Circle(float newX, float newY, float newRadius){	//3 paramter
    /*
    x = newX;
    y = newY;
    radius = newRadius;
    */
    
    setX(newX);
    setY(newY);
    setRadius(newRadius);
}
```

If this is the only constructor then every circle must be created with 3 float parameters.

If I say `Circle cir2;` or `Circle cir3(5,6);` I get the Error.

To avoid this situation, It is possible to create multiple constructions to receive different numbers of parameters but only if they have different parameters.

```c++
Circle::Circle(float newRadius){			//1 parameter
    setX(0);
    setY(0);
    setRadius(newRadius);
}

Circle::Circle(float newX, float newY){		//2 parameter
    setX(newX);
    setY(newY);
    setRadius(1);
}

Circle::Circle(){							//no parameter
	setX(0);
	setY(0);
	setRadius(1);
}
```

```c++
int main(){
    Circle c1;
    Circle c2(5);
    Circle c3(2,4);
    Circle c4(1,3,5);
}
```

|  x   |  y   | radius |
| :--: | :--: | :----: |
|  0   |  0   |   1    |
|  0   |  0   |   5    |
|  2   |  4   |   1    |
|  1   |  3   |   5    |

It has the problem that It is difficult to distinguish the order of the parameters.

We can combine 3 constructors into a single constructor using default parameters.

These give a default value to a parameter if the parameter does not exist.

```c++
Circle::Circle(float newX=2, float newY=2, float newRadius=1){
    setX(newX);
    setY(newY);
    setRadius(newRadius);
}
```

```
int main(){
    Circle c1;
    Circle c2(5);
    Circle c3(2,4);
    Circle c4(1,3,5);
}
```

|  x   |  y   | radius |
| :--: | :--: | :----: |
|  2   |  2   |   1    |
|  5   |  2   |   1    |
|  2   |  4   |   1    |
|  1   |  3   |   5    |

We need to be careful when writing multiple constructors and using default parameters.

Any ambiguity(모호함) will cause an error. If two constructors match the same parameters.

```c++
class Circle{
    float x, y, radius;
    Circle(float newX);
    Circle(float newRadius);
};//Error!!
```

They both have 1 parameters, so there is an error.

```c++
class Circle{
    float x, y, radius;
    Circle(float newRadius);
    Circle(float newX = 1, float newY =1);
};//Error
```

* Example

  ```c++
  #include <iostream>
  using namespace std;
  
  class Circle{
  private:
      float x, y, radius;
  public:
      Circle(float newRadius){
      	x = 1;
      	y = 1;
      	radius = newRadius;
  	}
      Circle(float newX = 1, float newY = 1){
  		x = newX;
  		y = newY;
  		radius = 1;
  	}
      void info(){
  		cout << x << endl << y << endl << radius << endl;
  	}
  };//Error
  
  int main(){
  	Circle c1(2);
  	c1.info();
  	return 0;
  }
  ```

![1568895208025](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1568895208025.png)

* Both constructors can accept one parameter so there is an error.
* Constructors are part of Validation.
* The instance should never have impossible data.
* Constructors ensure an instance has valid data when the instance is created.
* Setters prevent changing the data to an invalid value.

### Copy Constructor

```c++
void MultiplyByTwo(int n){
    n = n * 2;				//n becomes 12
}

int main(){
    int num=6;
    MultiplyByTwo(num);
    cout << num << endl;
}
//Print 6, not 12
```

After Ending of the function, `MultiplyByTwo` the `int n` destroyed.

![1569507824239](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1569507824239.png)

```cpp
void DoubleSize(Circle circ){
    circ.radius = circ.radius * 2;
}
int main(){
    Circle c1(1,1,6);
    DoubleSize(c1);			//make a copy of c1
}
```

After Ending of the function, `DoubleSize` the `Circle circ` destroyed.

![1569507896450](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1569507896450.png)

```cpp
class Student{
public:
    int ID;
    char * name;
};

void Update(Student stud){
    stud ID = 11111;
    strcpy(stud.name, "David");
}

int main(){
    Student s1;
    Upadate(s1);
    cout << s1.ID << endl;					//same
    cout << s1.name << endl;				//changed to "David" and if Student 										   have the destructor, it destroyed.
}
```

![1569507955411](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1569507955411.png)

This is why we need copy constructor.

A Copy constructor is used whenever an instance of a class is passed as a parameter to a function. It is useful if the class has pointers.

Copy constructor for `Student`

```cpp
Student::Student(Student &copy){
    int size = copy.strlen(copy.name);	 //copy.name : old space in memory
    name = new char[size];				//name : new space in memory
    strcpy(name, copy.name);  
}
```

객체에 포인터가 없는경우는 복사생성자가 필요없다. 하지만 포인터가 있는경우엔, 원본 데이터가 변경될 수 있고, 함수가 종료되는 과정에서 소멸자가 실행되어 원본 데이터가 사라질 수 있다.

If a class does not use pointers, Usually we don't need a destructor or copy constructor. However If a class uses pointers then we usually need both a copy constructor and destructor.

### Pointers

These three things are all the same.

1. Memory Location
2. Memory Address
3. Pointer

```cpp
int num;
int * ptr;
```

![1569507981708](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1569507981708.png)

`num` is an integer, `ptr` is a pointer to an integer.

```cpp
num = 5;
ptr = & num;
```

* `ptr` contains the location of `num`.

* `& num` is the location of `num`.

* `* ptr` is the value of `num=5`.

* **`*` and `&` are inverse operations.**  ex) `*(& num) = *(4000) = 5`.

* The `*` in `int *` is different from the `*` in `* ptr`.

```cpp
void MultiplyByTwo(int * n){
    *n = *n * 2;
}

int main(){
    int num = 6;
    MultiplyByTwo(& num);
    cout << num << endl;				//print 12, not 6
}
```

### Rewrite using Reference Parameters `&`

```cpp
void MultiplyByTwo(int & n){
    n = n * 2;
}

int main(){
    int num = 6;
    MultiplyByTwo(num);
    cout << num << endl;				//print 12, not 6
}
```

### Operator Over Loading

In `c++` we can create new definitions for almost any operator `+ - * / == = << >> ~= < >`

~~~cpp
class Fraction{
public:
    int numerator;
    int denominator;
    void setNumerDenom(int n, int d) : numerator(n), denominator(d){}		//setter
    Fraction operator*(Fraction f);
    bool operator==(Fraction f);
};

Fraction Fraction::operator*(Fraction f){
    Fraction result;
    result.numerator = numerator * f.numerator;
    result.denominator = denominator * f.denominator;
    
    int divnum = GCD(result.numerator, result.denominator);
    result.numerator /= divnum;
    result.denominator /= divnum;
    
    return result;
}

bool Fraction::operator==(Fraction f){
    /* 복잡하지만 알아보기 쉬운 방법
    bool result;
    if(numerator * f.denominator == f.numerator * denominator){
        result = true;
    }
    else{
        result = false;
    }
    return result;
    */ 
    
    // 간단한 한줄 처리 방법
    return (numerator * f.denominator == f.numerator * denominator);
}

Fraction Fraction::operator++(){
    ```
}

Fraction Fraction::operator++(int x){
    ```
}

int main(){
    Fraction f1, f2, f3;
    f1.setNumerDenom(2,3);					//f1 = 2/3
    f2.setNumerDenom(3,4);					//f2 = 3/4
    f3 = f1 * f2;							//f3 = 1/2 (create a new definition of *)
    
    if(f1 == f2){							//create a new definition of ==
        ```
    }
    
    Fraction f4, f5;
    f4.setNumerDenom(1,2);
    f5.setNumerDenom(1,2);
    
    f4++;
    ++f5;
}
~~~

### Static  Variable

A `static variable` is created when the program starts running and is destroyed when the program finishes.

```cpp
void fucntionA(){
    int x;		//created when functionA starts(called) and destroyed when functionA finishes
    static int y;	//created when program starts and destryed when porgram finishes
	x = 5;
	y = 6;
}
```

If function A exits and then is called again, X was lost, but Y keeps the same value.

### Static class variable

A `static class variable` is created when the program starts. Even if there are many instances of the class, there is always only one copy of the class variable until the program finishes.

Static class variables can be accessed before any instance is created.

Static class variables must be given an initial value.

```cpp
class Fraction{
    int numer;
    int denom;
    static int count;
    
    static int getCount();
    static void setCount(newCount);
    `
    `
    `
}
Fraction::count = 0;	//initialize

Fraction * createFraction{
    Fraction * f1 = new Fraction;
    Fraction::count ++;			
    //Possible to access Fraction::count, even before any Fraction was created. 
    //All functions use the same shared value of count
    `
    `
    `
}

static int Fraction::getCount(){
    int x = numer;		//ERROR
    int y = count;		//OK
}
```

### Static Class Functions (methods)

Static Class Functions can be called, even if there is no instance of the class.

Static Class Functions cannot access class variables except static class variables.



### Constant (상수)

Variable labeled as `const` can never be changed after they are created.

A `const` class variable can be initialized when an instance is created, but it cannot be changed after the instance is created.

```cpp
const int x = 5; 		//OK
x = 7 				   //ERROR
```

```cpp
class Square{
    const int color;
    `
    `
    `
}

Square::Square(){
    color = 6;		//Used to be OK, Now ERROR
}
Square::Square():color(6){			//The only way to initialize const variable

}
Square::Square(int x):color(x){		//The only way to initialize const variable

}
```

functions can have `const` pointer parameters and return `const` pointers.

```cpp
const int * functionB(const float * value){
    `
    `
    `    
}
```

This means the data pointed to cannot be changed.

A `const` function inside a class cannot changed any class variables.

```cpp
class Circle{
    float radius;
    float getRadius() const;
}

float Circle::getRadius() const{
    float x = radius; 	// OK
    radius = x + 1; 	//ERROR
}
```

Getter can be labeled as `const` because it doesn't have to changed the variables.

`Const` functions can only call other `const` functions and they cannot call `non-const` functions.

`Static` class function can only call other `static` functions, they cannot call `non-static` functions.



### Object Oriented Terms

`int num` = `type, variable`

`Circle c`  = `class, instance`

In C

```cpp
int max(){
    //function
}
```

In C++

```cpp
int Circle::getRadius(){
	//Method = class function
}
```



```cpp
class Date{
    int year, month, day;
    void setDate(int newyear, int newmonth, int newday);
    `
    `
    `
}

int main(){
    Date d1;
    d1.setDate(4,7,12);
    //Can be confused like (April 7, 2012) or (July 12, 2004)
}
```



### Cascading Function

Cascading Functions are designed to solve this confusion.

```cpp
Date d1;
d1.setYear(4).setMonth(7).setDay(12)
```

![1570706110391](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1570706110391.png)

How to write `setYear` `setMonth` `setDay` ?

Need to use **`this`**

**`this`** is a pointer to the current instance.

```cpp
float Circle::getRadius(){
    return radius;
}
```

```cpp
float Circle::getRadius(){
    return this->radius;
}
```



```cpp
Date Date::setYear(int newYear){
    this->year = newYear;
    return * this;			//this is the pointer, so using star
}
Date Date::setMonth(int newMonth){
    this->month = newMonth;
    return * this;			//this is the pointer, so using star
}
Date Date::setDay(int newDay){
    this->day = newDay;
    return * this;			//this is the pointer, so using star
}
```

This has a problem.

![1570706142498](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1570706142498.png)

We can get four copies of `d1`. and above 3 `d1`s popped away, so only the `year` changed.

To solve this problem, we can use pointer.

![1570706157393](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1570706157393.png)

```cpp
Date d1;
d1.setYear(4)->setMonth(7)->setDay(12)
```

```cpp
Date* Date::setYear(int newYear){
    this->year = newYear;
    return this;	
}
Date* Date::setMonth(int newMonth){
    this->month = newMonth;
    return this;
}
Date* Date::setDay(int newDay){
    this->day = newDay;
    return this;
}
```

Also it works(recommended)

```cpp
Date d1;
d1.setYear(4).setMonth(7).setDay(12)
```

```cpp
Date& Date::setYear(int newYear){
    this->year = newYear;
    return * this;			//this is the pointer, so using star
}
Date& Date::setMonth(int newMonth){
    this->month = newMonth;
    return * this;			//this is the pointer, so using star
}
Date& Date::setDay(int newDay){
    this->day = newDay;
    return * this;			//this is the pointer, so using star
}
```

### DRY Code(Don't Repeat Yourself)

> 중복 배제 는 소프트웨어 개발 원리의 하나로, 모든 형태의 정보 중복을 지양하는 원리이다. 특히 다층 구조 시스템에서 유용하다. 중복배제 원리는 한마디로 “모든 지식은 시스템 내에서 유일하고 중복이 없으며 권위있는 표상만을 가진다”는 말로 기술할 수 있다

### Inheritance (상속성)

<img src="C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1571308828028.png" alt="1571308828028" style="zoom:25%;" />

If `SubClass1` and `SubClass2` have the same variables & methods, We can move the shared variables and methods to a `SuperClass` 

Methods and Variables in the `SuperClass` only need to be written once, but they are available to all `SubClasses` 

`SubClass` "inherit" Methods and Variables from the `SuperClass`

In `C++` `SubClass` can have one more `SuperClass` but not `java`

`SubClass` can be other `SubClass` 's `SuperClass`

<img src="C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1571308855832.png" alt="1571308855832" style="zoom: 25%;" />

`Eagle` inherits from both `birds` and `animals`

* For Example

  <img src="C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1571308877278.png" alt="1571308877278" style="zoom: 25%;" />

  ```cpp
  class Shape{
  protected:				//only this class and sub class can access these variables
      float xCenter, yCenter;
  public:
      float getX() const;
      float getY() const;
      void setCenter(float newX, float newY);   
  }
  ```

  ```cpp
  class Circle: public Shape{	//indicates that circle is the sub class of shape
  private:
      float radius;
  public:
      float getRadius() const;
      void setRadius(float newRadius);
  }
  ```

  ```cpp
  class Square: public Shape{
  private:
      float sideLength;
  public:
      float getSideLength() const;
      void setSideLength(float newSideLength);
  }
  ```

  ```cpp
  int main(){
      Shape s1;
      Circle c1;
      
      c1.setCenter(1.5, 3.8);
      c1.setRadius(3.7);
      s1.setRadius(1.2);			//error
      
      return 0;
  }
  ```

* For Example

  **How to cook Eggs & Ramyeon in `C++`**

  ```cpp
  void Egg::breaking(){
      HitEggOnPot();
      OpenEggInPot();
  }
  
  void Egg::boiling(){
  	PutEggInPot();
      HeatPot();
  }
  
  void Ramyeon::breaking(){
      HoldRamyeonInHands();
      BendRamyeonUntilBreaks();
  }
  
  void Ramyeon::boiling(){
      PutRamyeonInPot();
      HeatPot();
  }
  
  int main(){
      Egg e1;
      Ramyeon r1;
      
      e1.breaking();
      e1.boiling();
      r1.breaking();
      r1.boiling();
      
      return 0;
  }
  ```

  This is not the DRY code. `Boiling` looks the same in both Egg & Ramyeon classes.

  But `Breaking` is already DRY code.

  ![1571308893605](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1571308893605.png)

  Then We do not need to make boiling method twice.

  But We should make the breaking method individually because they are different.

  ```cpp
  void Food::boiling(){
      PutFoodInPot();
      HeatPot();
  }
  
  void Egg::breaking(){
      PutEggInPot();
      HeatPot();
  }
  
  void Ramyeon::breaking(){
      HoldRamyeonInHands();
      BendRamyeonUntilBreaks();
  }
  
  int main(){
      Egg e1;
      Ramyeon r1;
      
      e1.breaking();
      e1.boiling();
      r1.breaking();
      r1.boiling();
      
      return 0;
  }
  ```

* Return to Square and Circle Example

  `getArea()`의 경우 이름은 같지만 면적을 구하는 내부 방식이 다르므로 따로 만들어야 한다.

### Polymorphism (다형성)

It is possible that two classes have methods with the same name, and the choice of class will be unknown at compile time.

```cpp
//perfectly legal code
int main(){
    Shape * s1;
    if(```){
        s1 = new Circle;
    }
    else{
        s1 = new Square;
    }
    cout << s1->getArea() << endl;
}
```

Polymorphism means **change**. Here the Shape changed to either a Square or a Circle.

So now `getArea()` could be a method in Circle or a method in Square.

While the program is running, `s1` must be tested be tested to decide if it is a Circle or Square. 

The Compiler must generate extra code to perform this test.

If it is possible that a method can be called without knowing the class at compile time then we must label this method as **Virtual**

```cpp
class Circle: public Shape{
private:
    float radius;
public:
    Circle();
    float getRadius() const;
    void setRadius(float newRadius);
    virtual float getArea() const;
}

virtual float Circle::getArea() const{
    return radius*radius*PI;
}
```

Virtual Method는 함수의 선언부분만 있고 구현부분이 없다. 이렇게 선언된 추상 메소드는 하위 클래스에서 구현되어 사용된다. 즉 각각의 하위 클래스에서 상속받은 추상 메소드를 서로 다른 방법으로 구현하게 된다.

We can write a virtual function in the `SuperClass` with no function body.

```cpp
class Shape{
protected:		
    float xCenter, yCenter;
public:
    float getX() const;
    float getY() const;
    void setCenter(float newX, float newY);  
    virtual float getArea() = 0;		//just declare with no body.
    //virtual float getArea() {};		//same with above.
}
```

If a virtual method has no body then it should be declared as :

`virtual float getArea() = 0;` or `virtual float getArea() {};`

Any virtual method with no body is called a **Pure Virtual** method.

A class with any **Pure Virtual** method is called and **abstract** class.

So the `Shape` is the **abstract** class.

We cannot create any instance of an abstract class.

A `SubClass` of an abstract class should include the body of the pure virtual functions.

If not, then the subclass is abstract.

In other words, any subclass of shape must include the body of `getArea()`, or the subclass becomes abstract.

An abstract class basically lists the required methods for its subclasses as pure virtual methods.

<img src="C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1572773179209.png" alt="1572773179209" style="zoom: 33%;" />

If an instance of the Superclass is created, there is only enough space allocated in memory for the superclass variables. And we can only use the superclass methods.

If an instance of the subclass is created, then memory is allocated for both Subclass and Superclass variables. And we can use both of the superclass and subclass methods.

It is possible to create a pointer to a super class which changes to a subclass when the instance is created. This is polymorphism.

After the instance is created, then the class cannot be changed. If there are multiple possible classes for an instance, then there could be multiple possible methods for the same code. 

If there are multiple possible methods then, the methods should be labeled as `virtual`.

If a virtual method has no body, then it is a pure virtual method. (수퍼클래스에서 사용)

A class with any pure virtual method is an abstract class.

We cannot create any instance of an abstract class. The purpose of an abstract class is to force any subclass to write its pure virtual methods.

### Exception Handling

Whenever a program has an error while running, this creates an `Exception`

* For example

  ```cpp
  int a, b, c;
  
  a << cin;
  b << cin;
  c = a/b; //if b==0, it creates exception
  ```

If `c++`, it is possible to generate our own new exceptions.

* For example

  ```cpp
  int month;
  month << cin;
  if(month < 1 || month > 12)
      throw runtime_error("Invalid month")
  ```

Exceptions are created using `throw`.

Exception can be caught, using `catch`.

If an exception is not caught, then the program quits or crashes.

When an exception is caught, then special `Error Handling` instructions can be run.

Use the word `try` to designate a region where an exception can occur. Every `try section` should have a `catch section`.

```cpp
int month;
cin >> month;
try{
    if(month < 1 || month > 12)
        throw runtime_error("Invalid month");
}catch(runtime_error){
    cout << "Invalid month";
}
```

Without catch, a `runtime_error` will cause the program to crash.

With `catch` we can print an Error message or do something else and then continue to run the program after a `runtime_error`.

If there is a `runtime_error` inside a function, and the error is not caught in the function then the function deos not finish and returns.

The function call then creates a `runtime_error` which can be caught.

```cpp
int getMonth(){
    int month;
    cin >> month;
    if(month < 1 || month > 12)
        throw runtime_error("Invalid month");
}


int main(){
    int mon;
    try{
        mon=getMonth();
    }catch(runtime_error){
        cout << "Invalid month";
    }
    
    return 0;
}
```

### File Processing

* Text File

  A Text File is a file which is readable by humans.

  The ASCII code of every character is between 0 and 127 (7 bit)

  

* Binary File

  A Binary File is a file which is unreadable by humans.

  The ASCII code of characters are between 0 and 255 (8 bit)

In `C` we write to a text file with `fprintf()`. We write to a binary file using `fwrite()`.

We read Text and Binary files with `fscanf()` and `fread()`.

Example of the Writing a Text file in `C` (this is available in `C++`)

```c
FILE * fptr;
if((fptr=fopen("filename.txt", "rw"))==NULL)
    printf("Error Opening File\n");
else
    fprintf(fptr, "File Data");
fclose(fptr);
```

In `C++` we can use `file streams`.

* `ofstream` : output file stream
* `ifstream` : input file stream
* `fstream` : input/output file stream

Example of the Writing a Text file in `C++`

```cpp
#include<iostream>
#include<fstream>
using namespace std;
ofstream file1;

try{
    file1.open("filename.txt", ios::out);
    if(!file1)
        throw FileOpenException;
}catch(FileOpenException){
    cerr << "Error Opening File" << endl;			//cout처럼 에러를 표시하는 것.
}
if(file1){
    cout << "File Opened Successfully" << endl;		//display in console
    file1 << "File Data" << endl;					//save the data
}
```

To open a binary file in `C++`

```cpp
fstream file2;

try{
    file2.open("filename.bin", ios::in|ios::out|ios::binary);	
    //input, output 모두 가능하며 또한 binary 파일이다
    if(!file2)
        throw FileOpenException;
}catch(FileOpenException){
    cerr << "Error Opening File" << endl;			//cout처럼 에러를 표시하는 것.
    return;
}
if(file1){
    cout << "File Opened Successfully" << endl;		//display in console
    file1 << "File Data" << endl;					//save the data
}

Circle c1;
c1.setX(5).setY(7).setRadius(1);

file2.write(reinterpret_cast<const char*>(& c1), sizeof(c1));

//Later I can read this circle from the same file.
Circle c4;

file2.read(reinterpret_cast<const char*>(& c4), sizeof(Circle)); //sizeof(c4) 가능
```

<img src="C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1572773210459.png" alt="1572773210459" style="zoom: 25%;" />

### Casting

Casting is the process of changing the type or class

In `C`

```cpp
int num = 5;
float val = (float)num; // this cast changes the bits in memory

Node * n1 = (Node *)malloc(sizeof(Node));
//this cast does not change any bits in memory
```

`C++` identifies several different kinds of casts.

`reinterpret_cast`, `static_cast`, `dynamic_cast`, `const_cast`

### `reinterpret_cast<const char*>`

changes a pointer to a different pointer without changing any memory.

### `static_cast`

used when memory is changed, for example `int`->`float`

### `dynamic_cast`

to change a pointer between superclass and subclass. The data in an instance might be rearranged by `dynamic_cast`, but the pointer does not changed.

for example

```cpp
Shape * sh1 = new Shape;				// 8 bytes;
Circle * c1;							// 12 bytes;

c1 = sh1;							//memory size가 달라서 error
c1 = dynamic_cast(Circle *)sh1;		//ok
```



### `const_cast`

A `const_cast` is used when a pointer to a cont is changed to point to be a non_const or a pointer to a non_cost is changed to point to be a const.

for example

```cpp
const int x=5;
int y = 7;
const int * p = &x;
*p = 3; 	//error

p = &y;
*p = 3;		//still error

p = const_cast<int *> &y;
*p = 3;		//ok
```

![1572773234766](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1572773234766.png)

```cpp
int * x; 								//4 bytes
char array[4] = {'x', 'y', 'z', 'a'};	//1 bytes * 4

x = reinterpret_cast <int *>array;
//두개의 사이즈가 같기때문에 char array에 담긴 데이터가 int형으로 생각된다.
```

`reinterpret_cast` is often used during file processing with a binary file.

```cpp
Circle * c1 = new Circle;
file1.write(reinterpret_cast<char *>(c1), sizeof(Circle))
```

### Random Access File

Sequential File processing involves Reading or Writing a File starting at the beginning , and processing the file in order. If a file contains regularly spaced data, then it may be possible to jump to the middle of the file and read or write a piece of data in the middle.

This is called Random Access File Processing

```cpp
Circle * array[20]
for(int i=0; i<20; i++){
	array[i] = new Circle;
	array[i]->setRadius(i);
}
for(int i=0; i<20; i++){
	file1.wirte(reinterpret_cast<char *>array[i], sizeof(Circle));
}		//Sequential File Access
file1.close();

file2.open(```);
file2.seekp(7*sizeof(Circle));				//pointer를 옮긴다.
//Jump to the 7th Circle.

Circle * c2 = new Circle;
file2.read(reinterpret_cast<char *>c2, sizeof(Circle));
cout << c2.getRadius();		// print 7
```

What is the relationship between a subclass and a superclass?

What is the relationship between a class and its class variable?

A class variable is a member of the class

we can use "is a" and "has a" to describe these two relationships

A circle is a shape

A circle has a radius

Therefore Shape should be a superclass of circle. and Radius should be a class variable.

### Templates in `C++`

In some situations we may want to use an instance which could be of any class.

For example, A Linked List which contains instances of any class. Then we may want to use Templates.

```cpp
LinkedList <Square *> squareList;
LinkedList <Fraction *> fractionList;
LinkedList <Car *> carList; 
```

How to make the `LinkedList` class?

Create a Template Class

```cpp
#include <iostream>
#include <cstring>
#include <stdexcept>
using namespace std;

template <typename T>	//T becomes the class chosen when the instance is created
class Node{
public:
	Node <T> * next;
	T data;
};

template <typename T>
class LinkedList{
private:
	Node <T> * head;
public:
	void push(T newItem){
		Node <T> * newNode = new Node <T>;
		newNode->data = newItem;
		newNode->next = head;
		head = newNode;
	}
	T pop(){
		T result = head->data;
		Node <T> * oldHead = head;
		head = head->next;
		delete oldHead;
		return result;
	}
};
	
int main(){
	LinkedList <int> intlist;
	LinkedList <float> floatlist;
	
	intlist.push(1);
	intlist.push(2);
	intlist.push(3);
	cout << intlist.pop() << endl;
	cout << intlist.pop() << endl;
	cout << intlist.pop() << endl;
	
	floatlist.push(1.1);
	floatlist.push(2.1);
	floatlist.push(3.1);
	floatlist.push(1.9);
	
	cout << floatlist.pop() << endl;
	cout << floatlist.pop() << endl;
	cout << floatlist.pop() << endl;
	cout << floatlist.pop() << endl;
	
    return 0;
}
```

![1572856281118](C:\Users\seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1572856281118.png)



![1572773253112](C:\Users\Seok\OneDrive - implement mission-critical ROI\자료3-2\객체지향 프로그래밍\1572773253112.png)



















