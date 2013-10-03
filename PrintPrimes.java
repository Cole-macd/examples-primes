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
      boolean isJPrime;
      int indexOfNotPrimes;
      int notPrimes[] = new int[sizeOfNotPrimes + 1];

      int currentOddNum = 1;
      int indexOfReferencePrime = 2;
      int squareOfPrime = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentOddNum = currentOddNum + 2;
          if (currentOddNum == squareOfPrime) {
        	notPrimes[indexOfReferencePrime] = currentOddNum;
            indexOfReferencePrime = indexOfReferencePrime + 1;
            squareOfPrime = listOfPrimes[indexOfReferencePrime] * listOfPrimes[indexOfReferencePrime];
          }
          
          isJPrime = true;
          for (indexOfNotPrimes = 2; indexOfNotPrimes < indexOfReferencePrime && isJPrime; indexOfNotPrimes++) {
            while (notPrimes[indexOfNotPrimes] < currentOddNum) {
              notPrimes[indexOfNotPrimes] = notPrimes[indexOfNotPrimes] + (listOfPrimes[indexOfNotPrimes] * 2);
            }
            if (notPrimes[indexOfNotPrimes] == currentOddNum) {
              isJPrime = false;
            }
          }
        } while (!isJPrime);
        
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

					 
