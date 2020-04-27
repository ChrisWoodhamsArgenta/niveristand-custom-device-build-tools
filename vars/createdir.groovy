def call(directory){
	def tokens = directory.tokenize("\")
	def finaldir = ""
	tokens.each{ token ->
		finaldir = finaldir + token + "\"
		bat "(mkdir $finaldir) ^& IF %ERRORLEVEL% LSS 8 SET ERRORLEVEL=0"
	}
}
