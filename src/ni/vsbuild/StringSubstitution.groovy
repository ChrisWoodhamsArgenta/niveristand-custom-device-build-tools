package ni.vsbuild

class StringSubstitution implements Serializable {

   public static String replaceStrings(text, lvVersion, additionalReplacements = [:]) {
      def replacements = [
            "labview_version": lvVersion,
            "veristand_version": lvVersion,
            "labview_short_version": lvVersion.substring(lvVersion.length() - 2),
      ]
      replacements << additionalReplacements

      def updatedText = text
      replacements.each { expression, value ->
         updatedText = updatedText.replaceAll("\\{$expression\\}", value)
      }
	return updatedText
	}
	  
	public static String addParameter(text, key, value) {

	  def updatedText = text.append("$key: $value")
      return updatedText
   }
}
