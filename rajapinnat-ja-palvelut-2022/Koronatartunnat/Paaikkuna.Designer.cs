
namespace Koronatartunnat
{
    partial class Paaikkuna
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.comboBoxAlue = new System.Windows.Forms.ComboBox();
            this.dateTimePickerTartuntapaiva = new System.Windows.Forms.DateTimePicker();
            this.labelTartunnat = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // comboBoxAlue
            // 
            this.comboBoxAlue.FormattingEnabled = true;
            this.comboBoxAlue.Location = new System.Drawing.Point(83, 83);
            this.comboBoxAlue.Name = "comboBoxAlue";
            this.comboBoxAlue.Size = new System.Drawing.Size(121, 23);
            this.comboBoxAlue.TabIndex = 0;
            this.comboBoxAlue.SelectedIndexChanged += new System.EventHandler(this.comboBoxAlue_SelectedIndexChanged);
            // 
            // dateTimePickerTartuntapaiva
            // 
            this.dateTimePickerTartuntapaiva.Location = new System.Drawing.Point(83, 166);
            this.dateTimePickerTartuntapaiva.Name = "dateTimePickerTartuntapaiva";
            this.dateTimePickerTartuntapaiva.Size = new System.Drawing.Size(200, 23);
            this.dateTimePickerTartuntapaiva.TabIndex = 1;
            this.dateTimePickerTartuntapaiva.ValueChanged += new System.EventHandler(this.dateTimePickerTartuntapaiva_ValueChanged);
            // 
            // labelTartunnat
            // 
            this.labelTartunnat.AutoSize = true;
            this.labelTartunnat.Location = new System.Drawing.Point(400, 128);
            this.labelTartunnat.Name = "labelTartunnat";
            this.labelTartunnat.Size = new System.Drawing.Size(38, 15);
            this.labelTartunnat.TabIndex = 2;
            this.labelTartunnat.Text = "label1";
            // 
            // Paaikkuna
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.labelTartunnat);
            this.Controls.Add(this.dateTimePickerTartuntapaiva);
            this.Controls.Add(this.comboBoxAlue);
            this.Name = "Paaikkuna";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox comboBoxAlue;
        private System.Windows.Forms.DateTimePicker dateTimePickerTartuntapaiva;
        private System.Windows.Forms.Label labelTartunnat;
    }
}

