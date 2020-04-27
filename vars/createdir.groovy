def call(directory){
	def tokens = directory.tokenize("\")
	println tokens.size()
	tokens.each{ token ->
		println token
	}
}
