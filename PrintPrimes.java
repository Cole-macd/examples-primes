public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage;
  int columnsPerPage;
  int WW;
  int sizeOfNotPrimes;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int WW, int sizeOfNotPrimes) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.WW  = WW;
    this.sizeOfNotPrimes = sizeOfNotPrimes;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
      printPrimes.calculatePrimes();
      printPrimes.printOutPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean isCurrentNumPrime; 
      int notPrimes[] = new int[sizeOfNotPrimes + 1]; 	//array that holds multiples of primes
      int currentOddNum = 1; 							//current number being checked for being prime
      int indexOfReferencePrime = 2;					//keeps track of how many prime multiples will be compared
      int squareOfPrime = 9;							//initialized to 9 because first prime is 3

      /*loops through until specified number of primes is found*/
      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentOddNum = currentOddNum + 2;
          
          /*updates current square number when current odd number is the same*/
          if (currentOddNum == squareOfPrime) {
        	notPrimes[indexOfReferencePrime] = currentOddNum; //stores current square number
            indexOfReferencePrime = indexOfReferencePrime + 1; //increases range of prime multiples being checked
            squareOfPrime = listOfPrimes[indexOfReferencePrime] * listOfPrimes[indexOfReferencePrime];
          }
          
          /*iterates through notPrimes array until reaching reference point*/
          isCurrentNumPrime = true;
          for (int indexOfNotPrimes = 2; indexOfNotPrimes < indexOfReferencePrime && isCurrentNumPrime; indexOfNotPrimes++) {
        	  
        	  /*increase multiples being checked so they are in the range of the current odd number*/
              while (notPrimes[indexOfNotPrimes] < currentOddNum) {
                notPrimes[indexOfNotPrimes] = notPrimes[indexOfNotPrimes] + (listOfPrimes[indexOfNotPrimes] * 2);
              }
              /*if current odd number is in the not prime multiples being checked then not prime*/
              if (notPrimes[indexOfNotPrimes] == currentOddNum) {
                isCurrentNumPrime = false;
              }
            } 
        } while (!isCurrentNumPrime); 
        /*upon exiting loop current number is prime*/
        listOfPrimes[primesFoundSoFar] = currentOddNum;
      }
    }
  
  
    public void printOutPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber + "\n");
          
          for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++) {
            for (int c = 0; c < columnsPerPage; c++) {
              if (rowOffset + c * rowsPerPage <= numberOfPrimes) {
                System.out.format("%10d", listOfPrimes[rowOffset + c * rowsPerPage]);
              }
            }
            System.out.println("");
          }
          
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}

					 
