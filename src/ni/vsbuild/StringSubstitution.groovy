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
	  
	public static String addParameter(text, key, value) {

	  updatedText = "$text" + "\n" + " $key: $value
      return updatedText
   }
}
