#include <iostream>
#include <ctime>
#include <cstdlib> 
using namespace std;

struct Elements{
	double *fx;
	double a;
	double b;
	double x1;
	double x2;
	double dx;
} elm_of_func;

Elements ReadPar(Elements st);
double Change_b(int b);
double* Calc(Elements st);
void Tab(Elements strct, int size);
void SS(Elements st, int size);
void Line(double* fx, int a, int b, int size);
void Print(int* mas, int size);

Elements ReadPar(Elements st){
	printf("Enter parameter a, please: ");
	scanf("%lf", &st.a);
	printf("Enter parameter b (not 0), please: ");
	scanf("%lf", &st.b);
	printf("Enter left limit of x, please: ");
	scanf("%lf", &st.x1);
	printf("Enter right limit of x, please: ");
	scanf("%lf", &st.x2);
	printf("Enter step, please: ");
	scanf("%lf", &st.dx);
	return st;
}

double Change_b(int b){
	printf("You entered 0...Enter parameter b (not 0), please: ");
	scanf("%d", &b);
	if(b == 0) b = Change_b(b);
	return b;
}

double* Calc(Elements st){
	double x = st.x1;
	int  i = 0;
	while(x <= st.x2 + st.dx * 0.001){
		if(x <= -0.5){
			st.fx[i] = st.a * x - st.b;
		} else {
			st.fx[i] = -1 * (x + st.a) / st.b;
		}
		x += st.dx;
		i++;
	}

	return st.fx;
}

void Tab(Elements strct, int size){
	char c1 = 186, c2 = 196;
	double x = strct.x1;
	printf("\n%c%-9c%c%-8s%c\n", c1, 'x', c1, "f(x)",c1);
	cout << c1 << string(9, c2) << c1 << string(8, c2) << c1 << endl;
	for(int i = 0; i < size; i++){
		printf("%c%-9.4lf%c%-8.3lf%c\n", c1, x, c1, strct.fx[i], c1);
		x += strct.dx;
		cout << c1 << string(9, c2) << c1 << string(8, c2) << c1 << endl;
	}
}

void SS(Elements st, int size){
	double s1, s2;
	for(int i = 0; i < size; i++){
		if(i % 2 == 0) s1 += st.fx[i];
		s2 += st.fx[i] * st.fx[i];
	}
	s1 /= (size + 1) / 2;
	s2 /= size;
	printf("\nS1 = %lf\nS2 = %lf\n", s1, s2);
	double *md = new double[size], smin = s1, smax = s2, bb = 0.15;
	if(s1 > s2){
		smin = s2;
		smax = s1;
	}
	double avr = (s1 + s2) / 2, step = bb * avr;
	srand(time(NULL));
	md[0] = ((smax - smin) * ((float)rand() / RAND_MAX)) + smin;
	for(int i = 1; i < size; i++){
		double min_rand = md[i-1] - step;
		double max_rand = md[i-1] + step;
		double val = (rand()%2 == 0) ? min_rand : max_rand;
		if(val > smax || val < smin) md[i] = (val == min_rand) ? max_rand : min_rand;
		else md[i] = val;
	}
	char c1 = 186, c2 = 196;
	cout << endl;
	for(int i = 0; i < size; i++){
		printf("%c%-4d%c%-8.3lf%c\n", c1, i + 1, c1, md[i], c1);
		cout << c1 << string(4, c2) << c1 << string(8, c2) << c1 << endl;
	}
	cout << endl;
	delete md;
}

void Line(double* fx, int a, int b, int size){
	int *values = new int[size];
	double max = INT_MIN;
	double min = INT_MAX;
	for(int i = 0; i < size; i++){
		if(fx[i] > max) max = fx[i];
		if(fx[i] < min) min = fx[i];
	}
	for(int i = 0; i < size; i++){
		values[i] = a + (fx[i] - min) * (b - a) / (max - min);
	}
	Print(values, size);
	delete values;
}

void Print(int* mas, int size){
	char c = 178;
	for(int i = 0; i < size; i++){
		printf("%-4d%c", mas[i], 179);
		if(mas[i] != 0){
			for(int j = 1; j < mas[i]; j++) printf("%c", 32);
			printf("%c\n", c);
		} else printf("\n");
	}
}

int main(){
	Elements strct = ReadPar(strct);
	if(strct.b == 0) strct.b = Change_b(strct.b);
	int size = ((strct.x2 + strct.dx * 0.001) - strct.x1) / strct.dx + 1;
	strct.fx = new double[size];
	strct.fx = Calc(strct);
	Tab(strct, size);
	SS(strct, size);
	Line(strct.fx, 0, 70, size);
	delete strct.fx;
	return 0;
}
