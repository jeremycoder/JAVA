/* 
Name: Turki Alsharari and Jeremy Mwangelwa
Emails: 
Group Number: 2
Course: CET 350 - 001 Technical Computing with Java
Spring 2016

This program counts the words in a file
and sums up all the integers in the file */

import java.io.*; //For input and output, console, keyboard, and files
import java.util.StringTokenizer; //For parsing the strings

class Program2
{
	static void stringToken(String inF, String outF)
	{
		//Looking to test FileReader
		
		FileInputStream fin; //Create object to read in file data
		FileOutputStream fout; //Create object to read data to a file
		String fileLine; //To extract from file lines
		String delimeters = "~`!@#$%^&*()_+={[}]|\\:\";<,>.? /'"; //Stores all delimeters
		String word = ""; //To hold word tokens
		int num = 0; //To hold integer tokens
		int numTotal = 0; //To hold sum of all integers
		int arrPos = 0; //To mark position of word array
		String token = ""; //To hold token strings
		String noWord = "(no word)"; //To tell user token is not a word
		String noInt = "(no int)";//To tell user token is not an integer
						
		//Attempt to open input file
		try{
			fin = new FileInputStream(inF); 
		}	catch(FileNotFoundException e) {
			System.out.println("\n\tERROR: cannot open input file");
			return;
			}
			
		//Attempt to open output file
		try{
			fout = new FileOutputStream(outF); 
		}	catch(FileNotFoundException e) {
			System.out.println("\n\tERROR: cannot open output file");
			return;
			}
			
		BufferedReader myBuffReader = new BufferedReader(new InputStreamReader(fin)); //Create buffer to read from file input stream
		StringTokenizer tokenizer; //Create string tokenizer object
			
		try {
		
		//fileLine = myBuffReader.readLine(); //...read the first line from the file and place it in fileLine
		//System.out.println("\tORIGINAL LINE: "+fileLine); //...show what's being read from file
		boolean escape = false; //To escape while loop
		
		System.out.println("\n\t======================PROCESSING TOKENS===================");
		
		while (escape == false) //While we can still read lines...
		{
			fileLine = myBuffReader.readLine(); //Read a line from bufferedReader and into firstLine
			System.out.println("\n\tORIGINAL LINE: " + fileLine); //Print out what is read
			
			if (fileLine == null) //If line retrieved is empty...
				escape = true; //...escape the loop
			else //Otherwise, process the token
			{
				tokenizer = new StringTokenizer(fileLine, delimeters); //Parse (tokenize) the string
				while (tokenizer.hasMoreTokens()) //While we can still get more tokens
				{
				token = tokenizer.nextToken(); //Get token
				//System.out.print("\tToken: " + token); //Show the token
				
				try{
				num = Integer.parseInt(token);//Try to convert token to an integer
				numTotal = numTotal + num; //Add all integers together
				System.out.print("\tToken: "+token+"\tWord: "+noWord+" \tInteger: "+num+"\t\tTotal: "+numTotal+"\n");
				
				} catch(NumberFormatException numFormatErr)//If conversion fails, process words
				{word = token;
				System.out.print("\tToken: "+token+"\tWord: "+word+"\t\tInteger: "+noInt+" \tTotal: "+numTotal+"\n");
				}
				
				
				
				}				
			}
			
		}
		
		System.out.println("\n\tSUCCESS! String tokenizing complete!");
		myBuffReader.close(); //Close the file reading stream

		
		} catch (IOException e2) {
			System.out.println("\n\tERROR READING OR WRITING TO FILE!");
		}
					
		try{
		fout.close(); 
		} catch (IOException e) {
			System.out.println("\n\tERROR closing output file");
		}
		
		try{
		fin.close();
		} catch (IOException e) {
			System.out.println("\n\tString Tokenizer says 'ERROR closing input file");
		}
		
	}
	
	public static void main(String args[]) throws Exception
	{		
	//==========================IF USER ENTERS BOTH ARGUMENTS=======================================================
		if (args.length >= 2) //Check if number of arguments on command line is 2 or greater
		{   
			boolean quit = false; //To use when escaping loop
			
			String inFile;	//To hold input file name
			String outFile; //Tohold output file name
			
			FileInputStream fin = null; //Create object to read files
			FileOutputStream fout = null; //Create object to write files
			
			String input; //To hold input read from keyboard
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)); //Create buffer to read input from keyboard

			if ( args[0].equals(args[1]) ) //If filenames from command line are equal, quit
			{
				System.out.println("\n\tERROR: Input file name same as output file name.");	//Print error because of same filenames		
			}
			else
			{
				inFile = args[0]; //Assign first command line argument to input file name
				outFile = args[1]; //Assign second command line argument to output file name
				
				File inFileObject = new File(args[0]); //Create inFileObject to be used for input files, takes first argument
				File outFileObject = new File(args[1]); //Create outFileObject to be used for output files, take second argument
				File newFileObject = null; //Will be used to temporarily store creation of a new file
				
				while (quit == false) //Do this only if quit equals false
				{
								
					while (!inFileObject.exists() ) //While input file does not exist...
					{
						System.out.println("\n\tInput file: " + inFileObject.getName() + " does not exist."); //Message if input file does not exist
						System.out.println("\tPlease enter valid input file.");
						System.out.println("\tTo quit, leave blank space and press [ENTER].\n\t");
						
						input = buffReader.readLine(); //Send input from buffer to input String variable
						
						if (input.length() == 0) //If entry left blank, quit
						{
							quit = true;
							break;
						}
						else
						{
							inFileObject = new File(input); //Assign inFileObject to the new file entered							
						}	
											
					}
					
					inFile = inFileObject.getName(); //Assign new input file name to inFile, inFile will be passed to String Tokenizer
					
					//================================NOW DEALING WITH OUTPUT FILES=================================
					
					while ( outFileObject.exists() ) //While output file already exists...
					{
						System.out.println("\n\tOutput file: " + outFileObject.getName() + " already exists." ); //...print message...
						System.out.println("\n\tINSTRUCTIONS: ");
						System.out.println("\tEnter 'O' or 'o' to overwrite existing output file."); //...with choices to overwrite...
						System.out.println("\tEnter 'N' or 'n' to enter a new output file name."); //...create a new file...
						System.out.println("\tLeave a blank space and press [ENTER] to quit."); //...or quit
						System.out.println("\tAny other entries will be rejected.\n\t");
						
						input = buffReader.readLine(); //Read entry from user's keyboard

						if (input.length() == 0) //If user enters nothing...
						{
							quit = true; //...quit...
							break; //...and get out of loop
						}
						
						while (input.length() > 0) //while users input is not nothing
						{
							//Read first character of entered string, if it equals 'N' or 'n' create new output file
							if ( String.valueOf(input.charAt(0) ).equals("N") || String.valueOf(input.charAt(0) ).equals("n"))
							{
								System.out.println("\n\tCREATING NEW OUTPUT FILE");
								System.out.println("\tYou decided to create a new output file name."); //User instructions
								System.out.println("\tEnter new output file name,"); //User prompt
								System.out.println("\tor leave a blank space and press [ENTER] to quit."); //...or quit
								System.out.println("\tOutput file name: \n\t");

								input = buffReader.readLine(); //Read entry from user's keyboard
								
								if (input.length() == 0) //If user enters nothing...
								{
									System.out.println("\tNOTHING ENTERED. Are you ready to quit?");
									System.out.println("\tTo quit, leave it blank and press [ENTER]");
									System.out.println("\tIf not, read instructions below.");
									quit = true; //...quit...
									break; //...and break out of while loop
								}
								
								if (input.equals(inFile) ) //If users input of output file name equals input file name
								{
									System.out.println("\tERROR: Output file name '" + input + "' is same as input file name." );//Error message
									quit = true; //quit
									break; //break out of while loop
								}
								
								outFileObject = new File(input); //Assign users input to outFileObject
																
								if ( outFileObject.exists() ) //If that file exists...
								{
									System.out.println("\tERROR:Output file already exists!"); //Print error message
									quit = false; //...do not quit...
									break; //...but break out of loop
								}
								
								//If none of the conditions above are met, confirm new out put file name, it will be created below
								System.out.println("\tNew output file: '"+outFileObject.getName() + "' confirmed." );
								quit = false;
								break;
							}
							//If user enters "O" or "o", overwrite the file
							else if ( String.valueOf(input.charAt(0) ).equals("O") || String.valueOf(input.charAt(0) ).equals("o"))
							{
								//Backup file is created with every overwritten file
								
								String renamedFile; //Create new string to hold name of renamed file
								renamedFile = outFileObject.getName(); //Call renamedFile the name of file about to be overwritten
								renamedFile = renamedFile + "_backup"; //Change renamedFile's name to filename_backup
								
								File renamedFileObject = new File(renamedFile); //Create new file object with back up file's name
								
								//outFile = outFileObject.getName(); //Store current file name before renaming file
								
								outFileObject.renameTo(renamedFileObject); //Rename file to file_backup
								
								System.out.println("\tOVERWRITING FILE.");
								System.out.println("\tBackup created, in case you made a mistake. Backup file named: ");
								System.out.println("\t" + renamedFileObject.getName() );
								
								quit = false;
								break;
							}
							else
							{
								System.out.println("\tINVALID ENTRY! READ INSTRUCTIONS.");
								break;
							}
							
						}
						
						
					}
					
					if (inFileObject.exists() && quit == false) //Only if input file exists, and quit is false do we create a new output file
					{
						outFileObject.createNewFile(); //Use the object to create a new file					
						System.out.println("\n\tNew output file '" + outFileObject.getName() + "' created"); //Tell user
						
						//newFileObject = outFileObject; //Copy outFileObject to a new file object
						outFile = outFileObject.getName(); //Get name of output File, will pass this to StringTokenizer
						outFileObject.delete(); //Delete the file so we can escape while (outFileObject.exists() ) loop			
						
					}
					
					//Only if input file exists and output file does not exist, will StringTokenizer be called
					if ( inFileObject.exists() && !outFileObject.exists() )
					{
						stringToken(inFile, outFile); //Call string tokenizer
						buffReader.close(); //Close bufferedReader once stringTokenizer has been called
					}
						
							
					quit = true; //Quit program
				
				}
				
			}
		}
		
		//==============================IF USER ENTERS ONLY ONE ARGUMENT===============================================
		
		else if (args.length == 1) //If user only entered one argument on command line
		{   
			boolean quit = false; //To use when escaping loop
			
			String inFile;	//To hold input file name
			String outFile; //Tohold output file name
			
			FileInputStream fin = null; //Create object to read files
			FileOutputStream fout = null; //Create object to write files
			
			String input; //To hold input read from keyboard
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)); //Create buffer to read input from keyboard
			
			System.out.println("\n\tInput file specified as '" + args[0] + "'. No output file specified."); //Inform user that...
			System.out.println("\tPlease enter name of output file."); //...an output file needs entered, or user...
			System.out.println("\tTo quit, leave it blank and just press [ENTER].\n\t"); //...can chooset to quit
			
			input = buffReader.readLine(); //Send input from buffer to input String variable

			if (input.length() == 0) //If user enters nothing...
			{
				System.out.println("\tNOTHING WAS ENTERED."); //...inform user
				quit = true; //Choose to quit...
			}
			else
			{
				//args[1] = input; //...otherwise, assign user's input to second command line argument
				
				if ( args[0].equals(input) ) //If filenames are equal, quit
				{
				System.out.println("\tERROR: Input file name same as output file name.");	//Print error because of same filenames		
				}
				else
				{
				inFile = args[0]; //Assign first command line argument to input file name
				outFile = input; //Assign second command line argument to output file name
				
				File inFileObject = new File(args[0]); //Create inFileObject to be used for input files, takes first argument
				File outFileObject = new File(input); //Create outFileObject to be used for output files, take second argument
				File newFileObject = null; //Will be used to temporarily store creation of a new file
				
					while (quit == false) //Do this only if quit equals false
					{
									
						while (!inFileObject.exists() ) //While input file does not exist...
						{
							System.out.println("\n\tInput file: " + inFileObject.getName() + " does not exist."); //Message if input file does not exist
							System.out.println("\tPlease enter valid input file.");
							System.out.println("\tTo quit, leave blank space and press [ENTER].\n\t");
							
							input = buffReader.readLine(); //Send input from buffer to input String variable
							
							if (input.length() == 0) //If entry left blank, quit
							{
								quit = true;
								break;
							}
							else
							{
								inFileObject = new File(input); //Assign inFileObject to the new file entered							
							}	
												
						}
						
						inFile = inFileObject.getName(); //Assign new input file name to inFile, inFile will be passed to String Tokenizer
						
						//================================NOW DEALING WITH OUTPUT FILES=================================
						
						while ( outFileObject.exists() ) //While output file already exists...
						{
							System.out.println("\n\tOutput file: " + outFileObject.getName() + " already exists." ); //...print message...
							System.out.println("\n\tINSTRUCTIONS: ");
							System.out.println("\tEnter 'O' or 'o' to overwrite existing output file."); //...with choices to overwrite...
							System.out.println("\tEnter 'N' or 'n' to enter a new output file name."); //...create a new file...
							System.out.println("\tLeave a blank space and press [ENTER] to quit."); //...or quit
							System.out.println("\tAny other entries will be rejected.\n\t");
							
							input = buffReader.readLine(); //Read entry from user's keyboard

							if (input.length() == 0) //If user enters nothing...
							{
								quit = true; //...quit...
								break; //...and get out of loop
							}
							
							while (input.length() > 0) //while users input is not nothing
							{
								//Read first character of entered string, if it equals 'N' or 'n' create new output file
								if ( String.valueOf(input.charAt(0) ).equals("N") || String.valueOf(input.charAt(0) ).equals("n"))
								{
									System.out.println("\n\tCREATING NEW OUTPUT FILE");
									System.out.println("\tYou decided to create a new output file name."); //User instructions
									System.out.println("\tEnter new output file name,"); //User prompt
									System.out.println("\tor leave a blank space and press [ENTER] to quit."); //...or quit
									System.out.println("\tOutput file name: \n\t");

									input = buffReader.readLine(); //Read entry from user's keyboard
									
									if (input.length() == 0) //If user enters nothing...
									{
										System.out.println("\tNOTHING ENTERED. Are you ready to quit?");
										System.out.println("\tTo quit, leave it blank and press [ENTER]");
										System.out.println("\tIf not, read instructions below.");
										quit = true; //...quit...
										break; //...and break out of while loop
									}
									
									if (input.equals(inFile) ) //If users input of output file name equals input file name
									{
										System.out.println("\tERROR: Output file name '" + input + "' is same as input file name." );//Error message
										quit = true; //quit
										break; //break out of while loop
									}
									
									outFileObject = new File(input); //Assign users input to outFileObject
																	
									if ( outFileObject.exists() ) //If that file exists...
									{
										System.out.println("\tERROR:Output file already exists!"); //Print error message
										quit = false; //...do not quit...
										break; //...but break out of loop
									}
									
									//If none of the conditions above are met, confirm new out put file name, it will be created below
									System.out.println("\tNew output file: '"+outFileObject.getName() + "' confirmed." );
									quit = false;
									break;
								}
								//If user enters "O" or "o", overwrite the file
								else if ( String.valueOf(input.charAt(0) ).equals("O") || String.valueOf(input.charAt(0) ).equals("o"))
								{
									//Backup file is created with every overwritten file
									
									String renamedFile; //Create new string to hold name of renamed file
									renamedFile = outFileObject.getName(); //Call renamedFile the name of file about to be overwritten
									renamedFile = renamedFile + "_backup"; //Change renamedFile's name to filename_backup
									
									File renamedFileObject = new File(renamedFile); //Create new file object with back up file's name
									
									//outFile = outFileObject.getName(); //Store current file name before renaming file
									
									outFileObject.renameTo(renamedFileObject); //Rename file to file_backup
									
									System.out.println("\tOVERWRITING FILE.");
									System.out.println("\tBackup created, in case you made a mistake. Backup file named: ");
									System.out.println("\t" + renamedFileObject.getName() );
									
									quit = false;
									break;
								}
								else
								{
									System.out.println("\tINVALID ENTRY! READ INSTRUCTIONS");
									break;
								}
								
							}
							
							
						}
						
						if (inFileObject.exists() && quit == false) //Only if input file exists, and quit is false do we create a new output file
						{
							outFileObject.createNewFile(); //Use the object to create a new file					
							System.out.println("New output file: " + outFileObject.getName() + " created"); //Tell user
							
							//newFileObject = outFileObject; //Copy outFileObject to a new file object
							outFile = outFileObject.getName(); //Get name of output File, will pass this to StringTokenizer
							outFileObject.delete(); //Delete the file so we can escape while (outFileObject.exists() ) loop			
							
						}
						
						//Only if input file exists and output file does not exist, will StringTokenizer be called
						if ( inFileObject.exists() && !outFileObject.exists() )
						{
							stringToken(inFile, outFile); //Call string tokenizer
							buffReader.close(); //Close the bufferedReader
						}
							
								
						quit = true; //Quit program
					
					}
				
				}
			}
		
		}

			
		//=============================IF USER ENTERS NOTHING================================================
		else  //If user entered nothing on command line
		{   
			boolean quit = false; //To use when escaping loop
			
			String[] fileName = new String[2]; //Create string array to hold user input, will be used like command line arguments
			fileName[0] = ""; //Assign nothing to both elements of array
			fileName[1] = "";
			
			String inFile;	//To hold input file name
			String outFile; //Tohold output file name
			
			FileInputStream fin = null; //Create object to read files
			FileOutputStream fout = null; //Create object to write files
			
			String input; //To hold input read from keyboard
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)); //Create buffer to read input from keyboard
			
			System.out.println("\n\tNo input file specified. No output file specified."); //Inform user that...
			System.out.println("\tPlease enter name of input file."); //...an output file needs entered, or user...
			System.out.println("\tTo quit, leave it blank and just press [ENTER].\n\t"); //...can chooset to quit
			
			input = buffReader.readLine(); //Send input from buffer to input String variable
			
			fileName[0] = input; //Assign user entry into first element as input file

			if (fileName[0].length() == 0) //If user enters nothing for input file name...
			{
				System.out.println("\n\tNOTHING WAS ENTERED."); //...inform user
				quit = true; //Choose to quit...
			}
			else if (fileName[0].length() != 0) //If user entered something...
			{
				System.out.println("\n\tNo output file specified."); //Inform user that...
				System.out.println("\tPlease enter name of output file."); //...an output file needs entered, or user...
				System.out.println("\tTo quit, leave it blank and just press [ENTER].\n\t"); //...can chooset to quit
				
				input = buffReader.readLine(); //Send input from buffer to input String variable
				
				fileName[1] = input; //Assign user entry into second element as output file
				
				if (fileName[1].length() == 0) //If user enters nothing for output file name...
				{	
				System.out.println("\n\tNOTHING WAS ENTERED."); //...inform user
				quit = true; //Choose to quit...
				}
				
			}
			
			if (fileName[1].length() != 0) //If user entered something for output file name
			{
				if ( fileName[0].equals(fileName[1]) ) //If filenames are equal, quit
				{
				System.out.println("\tERROR: Input file name same as output file name.");	//Print error because of same filenames		
				}
				else
				{
				inFile = fileName[0]; //Assign first command line argument to input file name
				outFile = fileName[1]; //Assign second command line argument to output file name
				
				File inFileObject = new File(fileName[0]); //Create inFileObject to be used for input files, takes first argument
				File outFileObject = new File(fileName[1]); //Create outFileObject to be used for output files, take second argument
				File newFileObject = null; //Will be used to temporarily store creation of a new file
				
					while (quit == false) //Do this only if quit equals false
					{
									
						while (!inFileObject.exists() ) //While input file does not exist...
						{
							System.out.println("\n\tInput file: " + inFileObject.getName() + " does not exist."); //Message if input file does not exist
							System.out.println("\tPlease enter valid input file.");
							System.out.println("\tTo quit, leave blank space and press [ENTER].\n\t");
							
							input = buffReader.readLine(); //Send input from buffer to input String variable
							
							if (input.length() == 0) //If entry left blank, quit
							{
								quit = true;
								break;
							}
							else
							{
								inFileObject = new File(input); //Assign inFileObject to the new file entered							
							}	
												
						}
						
						inFile = inFileObject.getName(); //Assign new input file name to inFile, inFile will be passed to String Tokenizer
						
						//================================NOW DEALING WITH OUTPUT FILES=================================
						
						while ( outFileObject.exists() ) //While output file already exists...
						{
							System.out.println("\n\tOutput file: " + outFileObject.getName() + " already exists." ); //...print message...
							System.out.println("\n\tINSTRUCTIONS: ");
							System.out.println("\tEnter 'O' or 'o' to overwrite existing output file."); //...with choices to overwrite...
							System.out.println("\tEnter 'N' or 'n' to enter a new output file name."); //...create a new file...
							System.out.println("\tLeave a blank space and press [ENTER] to quit."); //...or quit
							System.out.println("\tAny other entries will be rejected.\n\t");
							
							input = buffReader.readLine(); //Read entry from user's keyboard

							if (input.length() == 0) //If user enters nothing...
							{
								quit = true; //...quit...
								break; //...and get out of loop
							}
							
							while (input.length() > 0) //while users input is not nothing
							{
								//Read first character of entered string, if it equals 'N' or 'n' create new output file
								if ( String.valueOf(input.charAt(0) ).equals("N") || String.valueOf(input.charAt(0) ).equals("n"))
								{
									System.out.println("\n\tCREATING NEW OUTPUT FILE");
									System.out.println("\tYou decided to create a new output file name."); //User instructions
									System.out.println("\tEnter new output file name,"); //User prompt
									System.out.println("\tor leave a blank space and press [ENTER] to quit."); //...or quit
									System.out.println("\tOutput file name: \n\t");

									input = buffReader.readLine(); //Read entry from user's keyboard
									
									if (input.length() == 0) //If user enters nothing...
									{
										System.out.println("\tNOTHING ENTERED. Are you ready to quit?");
										System.out.println("\tTo quit, leave it blank and press [ENTER]");
										System.out.println("\tIf not, read instructions below.");
										quit = true; //...quit...
										break; //...and break out of while loop
									}
									
									if (input.equals(inFile) ) //If users input of output file name equals input file name
									{
										System.out.println("\tERROR: Output file name '" + input + "' is same as input file name." );//Error message
										quit = true; //quit
										break; //break out of while loop
									}
									
									outFileObject = new File(input); //Assign users input to outFileObject
																	
									if ( outFileObject.exists() ) //If that file exists...
									{
										System.out.println("\tERROR:Output file already exists!"); //Print error message
										quit = false; //...do not quit...
										break; //...but break out of loop
									}
									
									//If none of the conditions above are met, confirm new out put file name, it will be created below
									System.out.println("\n\tNew output file: '"+outFileObject.getName() + "' confirmed." );
									quit = false;
									break;
								}
								//If user enters "O" or "o", overwrite the file
								else if ( String.valueOf(input.charAt(0) ).equals("O") || String.valueOf(input.charAt(0) ).equals("o"))
								{
									//Backup file is created with every overwritten file
									
									String renamedFile; //Create new string to hold name of renamed file
									renamedFile = outFileObject.getName(); //Call renamedFile the name of file about to be overwritten
									renamedFile = renamedFile + "_backup"; //Change renamedFile's name to filename_backup
									
									File renamedFileObject = new File(renamedFile); //Create new file object with back up file's name
									
									//outFile = outFileObject.getName(); //Store current file name before renaming file
									
									outFileObject.renameTo(renamedFileObject); //Rename file to file_backup
									
									System.out.println("\tOVERWRITING FILE.");
									System.out.println("\tBackup created, in case you made a mistake. Backup file named: ");
									System.out.println("\t" + renamedFileObject.getName() );
									
									quit = false;
									break;
								}
								else
								{
									System.out.println("\tINVALID ENTRY! READ INSTRUCTIONS");
									break;
								}
								
							}
							
							
						}
						
						if (inFileObject.exists() && quit == false) //Only if input file exists, and quit is false do we create a new output file
						{
							outFileObject.createNewFile(); //Use the object to create a new file					
							System.out.println("\tNew output file '" + outFileObject.getName() + "' created"); //Tell user
							
							//newFileObject = outFileObject; //Copy outFileObject to a new file object
							outFile = outFileObject.getName(); //Get name of output File, will pass this to StringTokenizer
							outFileObject.delete(); //Delete the file so we can escape while (outFileObject.exists() ) loop			
							
						}
						
						//Only if input file exists and output file does not exist, will StringTokenizer be called
						if ( inFileObject.exists() && !outFileObject.exists() )
						{
							stringToken(inFile, outFile); //Call string tokenizer
							buffReader.close(); //Close bufferedReader once program is done
						}
							
								
						quit = true; //Quit program
					
					}
				
				}
			}
		
		}

		
		System.out.println("\n\tQUITTING");
	}
	
	
}