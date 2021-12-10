using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Elokuvasovellus
{
    class TextBoxWithDefaultText
    {
        public string DefaultText { get; set; }
        public string Name { get; }
        public TextBoxBase TextBox { get; }
        public bool IsNumber { get; }
        public bool IsDefault { get { return TextBox.Text == DefaultText; } }
        public bool IsEmpty { get { return string.IsNullOrWhiteSpace(TextBox.Text); } }

        public TextBoxWithDefaultText(TextBoxBase textBox, string name, string defaultText, bool isNumber = false)
        {
            TextBox = textBox;
            Name = name;
            DefaultText = defaultText;
            TextBox.Text = defaultText;
            IsNumber = isNumber;
        }

        public void SetDefault()
        {
            TextBox.Text = DefaultText;
        }

        public override string ToString()
        {
            return Name;
        }
    }
}
